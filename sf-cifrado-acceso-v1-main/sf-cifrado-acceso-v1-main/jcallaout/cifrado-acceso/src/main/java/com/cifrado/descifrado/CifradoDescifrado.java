package com.cifrado.descifrado;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.util.Arrays;


/**
 * Class in charge of carrying out the process of encode response and
 * decode request.
 * 
 * @author B198609
 * 
 *
 */
public class CifradoDescifrado implements Execution {

	private static final String XMLOUT = "xmlout";
	private static final String CONTRAPARTE_LLAVES = "flow.response.llaves";
	private static final String CURRENT_FLOW_NAME = "current.flow.name";
	private static final String RSA = "RSA";
	private static final String RSA_ECB_PKCS1PADDING_ALGORITMO = "RSA/ECB/PKCS1Padding";
	private static final String REQUEST_CONTENT = "request.content";
	private static final String RESPONSE_CONTENT = "response.content";
	private static final String CAMPOS_CIFRAR_RESPONSE = "flow.camposCifrar.response";
	private static final String CAMPOS_CIFRAR_AES_RESPONSE = "flow.camposCifrarAes.response";
	private static final String ALGORITMO_TIPO_CIFRADO = "flow.algoritmoTipoCifrado";
	
	private static final String ENCODING_UTF8 = "UTF-8";
    private static final String AES_KEY = "AES";
    private static final String ALGORITHM_AES = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM_HMAC = "HmacSHA256";
    private static final int IV_SIZE = 16;
	
	private String camposCifrarResponse;
	private String camposCifrarAesResponse;
	private StringBuilder jsonPathResponse = new StringBuilder("$");
	private int contadorResponse = 0;
	private int contadorRequest = 0;
	private String algoritnoTipoCifrado;
	private Map<String, String> camposCifrarMap;
	private Map<String, String> camposCifrarAesMap;

	public ExecutionResult execute(MessageContext msgContext, ExecutionContext execContext) {
		try {
			
			String currentFlowName = msgContext.getVariable(CURRENT_FLOW_NAME);
			String llaves = msgContext.getVariable(CONTRAPARTE_LLAVES);

			Llavero llavero = extraerLlaves(llaves);

			camposCifrarResponse = msgContext.getVariable(CAMPOS_CIFRAR_RESPONSE);
			
			camposCifrarAesResponse = msgContext.getVariable(CAMPOS_CIFRAR_AES_RESPONSE);
			
			algoritnoTipoCifrado = msgContext.getVariable(ALGORITMO_TIPO_CIFRADO);
			
			if(algoritnoTipoCifrado == null) {
				algoritnoTipoCifrado  = RSA_ECB_PKCS1PADDING_ALGORITMO;
			}

					
			if (!currentFlowName.equalsIgnoreCase("PostFlow")) {
								
				String request = msgContext.getVariable(REQUEST_CONTENT);
				if (request != null && !request.isEmpty()) {
					Map<String, Object> requestObjeto = convertirRequestResponseObjeto(request);
					descifrarCamposRequest(requestObjeto, llavero);

					String requestJson = convertirRequestResponseJson(requestObjeto);
					msgContext.setVariable("flow.debug.request", requestJson);

					msgContext.setVariable(REQUEST_CONTENT, requestJson);

					msgContext.setVariable("request.header.Content-Length", requestJson.getBytes("UTF-8").length);
				}
			} else if (currentFlowName.equalsIgnoreCase("PostFlow")) {
				
				camposCifrarMap = generarMapCamposCifrar(camposCifrarResponse);
				camposCifrarAesMap = generarMapCamposCifrar(camposCifrarAesResponse);
				
				String response = msgContext.getVariable(RESPONSE_CONTENT);
				Map<String, Object> responseObjeto = convertirRequestResponseObjeto(response);
				cifrarCamposResponse(responseObjeto, llavero, "");

				String responseJson = convertirRequestResponseJson(responseObjeto);
				msgContext.setVariable("flow.debug.response", responseJson);

				msgContext.setVariable(RESPONSE_CONTENT, convertirRequestResponseJson(responseObjeto));

				msgContext.setVariable("response.header.Content-Length", responseJson.getBytes("UTF-8").length);
			}

			return ExecutionResult.SUCCESS;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			msgContext.setVariable(XMLOUT, " Error: " + sw.toString());
			return ExecutionResult.SUCCESS;
		}
	}

	/**
	 * Get the keys for encode y decode the request or response.
	 * 
	 * @param jsonllave
	 * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public Llavero extraerLlaves(String jsonllave) throws JsonMappingException, JsonProcessingException {
		/*Gson gson = new Gson();

		Llave llave = gson.fromJson(jsonllave, Llave.class);*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Llavero llavero = objectMapper.readValue(jsonllave, Llavero.class);

		return llavero;

	}

	/**
	 * Convert JSON to object.
	 * 
	 * @param jsonRequestResponse
	 * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public Map<String, Object> convertirRequestResponseObjeto(String jsonRequestResponse) throws JsonMappingException, JsonProcessingException {
		/*Gson gson = new Gson();

		@SuppressWarnings("unchecked")
		Map<String, Object> requestResponse = gson.fromJson(jsonRequestResponse, Map.class);*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> requestResponse = objectMapper.readValue(jsonRequestResponse, Map.class);

		return requestResponse;

	}

	/**
	 * Convert object to JOSN.
	 * 
	 * @param objetoRequestResponse
	 * @return
	 * @throws JsonProcessingException 
	 */
	public String convertirRequestResponseJson(Object objetoRequestResponse) throws JsonProcessingException {
		/* Gson gson = new Gson();

		String requestResponseJson = gson.toJson(objetoRequestResponse);*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		String requestResponseJson = objectMapper.writeValueAsString(objetoRequestResponse);

		return requestResponseJson;

	}

	/**
	 * Decode fields the request.
	 * 
	 * @param request
	 * @param accesoPrivadoCliente
	 */
	public void descifrarCamposRequest(Map<String, Object> request, Llavero llavero) {
		request.forEach((clave, valor) -> {
			System.out.println(clave);
			System.out.println(valor);

			if (valor instanceof LinkedHashMap<?, ?>) {
				System.out.println("linkend hijo");
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) valor;
				descifrarCamposRequest(linkedHashMap, llavero);
			} else if (valor instanceof ArrayList<?>) {
				System.out.println("array list restructura");
				contadorRequest = 0;
				((ArrayList<?>) valor).forEach((elementos) -> {
					if (elementos instanceof String) {
				      try {
				    	  String campo = desencriptarRsa(String.valueOf(elementos), llavero.getAccesoPrivadoCliente());
						  ((ArrayList) valor).set(contadorRequest, campo);
					  } catch (Exception e) {
						  String campo = decryptAes(llavero.getAccesoSimetrico(), llavero.getCodigoAutentificacionHash(), String.valueOf(elementos));
						  ((ArrayList) valor).set(contadorRequest, campo);
					  }
					} else if (elementos instanceof LinkedHashMap) {
					  LinkedHashMap<String, Object> linkedTreeMap = (LinkedHashMap<String, Object>) elementos;
					  descifrarCamposRequest(linkedTreeMap, llavero);
					}
					contadorRequest++;
				});
			} else if (!(valor instanceof Double) & !(valor instanceof Boolean) & !(valor instanceof Integer)) {
				
				try {
					request.replace(clave, desencriptarRsa(String.valueOf(valor), llavero.getAccesoPrivadoCliente()));
				} catch (Exception e) {
					request.replace(clave, decryptAes(llavero.getAccesoSimetrico(), llavero.getCodigoAutentificacionHash(), String.valueOf(valor)));
				}
			}
		});
	}

	/**
	 * Encode fields the response.
	 * 
	 * @param response
	 * @param accesoPublicoBackend
	 */
	public void cifrarCamposResponse(Map<String, Object> response, Llavero llavero, String path) {
		if (camposCifrarResponse != null || camposCifrarAesResponse != null) {
			response.forEach((clave, valor) -> {
				System.out.println(clave);
				System.out.println(valor);

				System.out.println(path);
				if (path.endsWith("]")) {
					jsonPathResponse = new StringBuilder("$");
					jsonPathResponse.append(path);
				} else if (!jsonPathResponse.toString().equals("$" + path)) {
					jsonPathResponse.append(path);
				}

				jsonPathResponse.append(".");
				jsonPathResponse.append(clave);

				if (valor instanceof LinkedHashMap<?, ?>) {
					System.out.println("linkend hijo restructura");
					@SuppressWarnings("unchecked")
					LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) valor;
					cifrarCamposResponse(linkedHashMap, llavero,
							new StringBuilder(path).append(".").append(clave).toString());
				} else if (valor instanceof ArrayList<?>) {
					System.out.println("array list restructura");
					System.out.println("tipo array list restructura: " + valor.getClass());
					contadorResponse = 0;
					((ArrayList<?>) valor).forEach((elementos) -> {
						System.out.println("tipo de clase:"+ elementos.getClass());
						if (elementos instanceof String) {
							System.out.println("jsonPath: " + jsonPathResponse + "[*]");
							if (camposCifrarResponse != null && validarCampoCifrar(camposCifrarMap, jsonPathResponse.toString() + "[*]")) {
								
							  ((ArrayList) valor).set(contadorResponse, encriptarRsa(String.valueOf(elementos), llavero.getAccesoPublicoBackend()));
								
							}  else if (camposCifrarAesResponse != null && validarCampoCifrar(camposCifrarAesMap, jsonPathResponse.toString())) {
								 ((ArrayList) valor).set(contadorResponse, encryptAes(llavero.getAccesoSimetrico(), llavero.getCodigoAutentificacionHash(), String.valueOf(elementos)));
							}
						} else if (elementos instanceof LinkedHashMap)  {
						  LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) elementos;
						  cifrarCamposResponse(linkedHashMap, llavero,
								new StringBuilder(path).append(".").append(clave).append("[*]").toString());
						}
						contadorResponse++;
					});
				} else if (!(valor instanceof Double) & !(valor instanceof Boolean) & !(valor instanceof Integer)) {
					System.out.println("jsonPath: " + jsonPathResponse);
					if (camposCifrarResponse != null && validarCampoCifrar(camposCifrarMap, jsonPathResponse.toString())) {
						response.replace(clave, encriptarRsa(String.valueOf(valor), llavero.getAccesoPublicoBackend()));
					} else if (camposCifrarAesResponse != null && validarCampoCifrar(camposCifrarAesMap, jsonPathResponse.toString())) {
						response.replace(clave, encryptAes(llavero.getAccesoSimetrico(), llavero.getCodigoAutentificacionHash(), String.valueOf(valor)));
					}
					
				}
				jsonPathResponse = new StringBuilder("$");

			});
		}
	}

	/**
	 * Encode the text RSA
	 * 
	 * @param valorCampo
	 * @param stringPublicKey
	 * @return String
	 * @throws CifradoDescifradoException
	 */
	public String encriptarRsa(String valorCampo, String stringPublicKey) {
		try {

			// ClassLoader classLoader = getClass().getClassLoader();
			// byte[] bytesArchivo =
			// IOUtils.toByteArray(classLoader.getResourceAsStream(LLAVE_CIFRADO_PUBLICA));
			byte[] bytesPublicKey = Base64.getDecoder().decode(stringPublicKey);

			X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(bytesPublicKey);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PublicKey llavePublica = keyFactory.generatePublic(publicSpec);
			Cipher cipher = Cipher.getInstance(algoritnoTipoCifrado);
			cipher.init(Cipher.ENCRYPT_MODE, llavePublica);

			return Base64.getEncoder()
					.encodeToString(cipher.doFinal(valorCampo.getBytes(StandardCharsets.UTF_8.toString())));
		} catch (Exception e) {
			// throw new CifradoDescifradoException("No ha sido posible generar el
			// cifrado");
			//return valorCampo;
		}

		return valorCampo;
	}

	/**
	 * 
	 * Decode the text RSA with private key sub.
	 * 
	 * @param valorCifrado
	 * @param privateKey
	 * @return
	 * @throws CifradoDescifradoException
	 */
	public String desencriptarRsa(String valorCifrado, String privateKey) throws Exception {
		//try {

			byte[] bytesPrivateKey = Base64.getDecoder().decode(privateKey);

			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytesPrivateKey);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			PrivateKey llavePrivada = keyFactory.generatePrivate(keySpec);

			Cipher cipher = Cipher.getInstance(algoritnoTipoCifrado);
			cipher.init(Cipher.DECRYPT_MODE, llavePrivada);

			return new String(cipher.doFinal(Base64.getDecoder().decode(valorCifrado)),
					StandardCharsets.UTF_8.toString());
		//} catch (Exception e) {

			// throw new CifradoDescifradoException("No ha sido posible descifrar el
			// texto.");
		//}
		//return valorCifrado;
	}
	
	/**
	 * Encrypt with symmetrical key.
	 * 
	 * @param aesKeyBase64
	 * @param hmacKeyBase64
	 * @param plainText
	 * @return
	 */
	public String encryptAes(String aesKeyBase64, String hmacKeyBase64,String valorCampo) {
        try {
            SecretKeySpec aesKey = new SecretKeySpec(Base64.getDecoder().decode(aesKeyBase64.getBytes(ENCODING_UTF8)), AES_KEY);
            SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(hmacKeyBase64.getBytes(ENCODING_UTF8)), ALGORITHM_HMAC);
            
            byte[] iv = generarInitializationVector();
            
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));
            
            byte[] plainText= valorCampo.getBytes(ENCODING_UTF8);
            
            byte[] cipherText = cipher.doFinal(plainText);
            byte[] iv_cipherText = concatenateBytes(iv, cipherText);
            byte[] hmac = generarHMAC(hmacKey, iv_cipherText);
            byte[] iv_cipherText_hmac = concatenateBytes(iv_cipherText, hmac);
    
            byte[] iv_cipherText_hmac_base64 = Base64.getEncoder().encode(iv_cipherText_hmac);
            return new String(iv_cipherText_hmac_base64, ENCODING_UTF8);
        
        } catch (Exception e) {
            //throw new CifradoDescifradoException("Incidente al cifrar el parametro");
        }
        
        return valorCampo;
        
    }
	
	/**
	 * 
	 * Decode with symmetrical key.
	 * 
	 * @param aesKeyBase64
	 * @param hmacKeyBase64
	 * @param iv_cipherText_hmac
	 * @return
	 */
	public String decryptAes(String aesKeyBase64, String hmacKeyBase64, String valorCifrado) {
        try {
            SecretKeySpec aesKey = new SecretKeySpec(Base64.getDecoder().decode(aesKeyBase64.getBytes(ENCODING_UTF8)), AES_KEY);
            SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(hmacKeyBase64.getBytes(ENCODING_UTF8)), ALGORITHM_HMAC);
        
            int macLength = obtenerHMACLength(hmacKey);
            
            byte[] iv_cipherText_hmac = Base64.getDecoder().decode(valorCifrado.getBytes(ENCODING_UTF8));
            int cipherTextLength = iv_cipherText_hmac.length - macLength;
            
            byte[] iv = Arrays.copyOf(iv_cipherText_hmac, IV_SIZE);
            byte[] cipherText = Arrays.copyOfRange(iv_cipherText_hmac, IV_SIZE, cipherTextLength);
            byte[] iv_cipherText = concatenateBytes(iv, cipherText);
            byte[] receivedHMAC = Arrays.copyOfRange(iv_cipherText_hmac, cipherTextLength, iv_cipherText_hmac.length);
            byte[] calculatedHMAC = generarHMAC(hmacKey, iv_cipherText);
            
            if(Arrays.areEqual(receivedHMAC, calculatedHMAC)) {
                Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
                cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
                byte[] plainText = cipher.doFinal(cipherText);
                return new String(plainText,ENCODING_UTF8);
            } else {
                //throw new CifradoDescifradoException("HMAC Validation failed");
            	return valorCifrado;
            }
            
        } catch (Exception e) {
            //throw new CifradoDescifradoException("Incidente al descifrar el parametro");
        	 //return valorCifrado;
        }
        return valorCifrado;
        
    }
	
	/**
	 * 
	 * Generated initial vector.
	 * 
	 * @return
	 */
	private byte[] generarInitializationVector() {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return iv;
    }

	/**
	 * 
	 * Concatenate bytes.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
    private byte[] concatenateBytes(byte[]first, byte[] second) {
        byte [] concatBytes = new byte[first.length + second.length];
        System.arraycopy(first, 0, concatBytes, 0, first.length);
        System.arraycopy(second, 0, concatBytes, first.length, second.length);
        return concatBytes;
    }

    /**
     * 
     * Generated HMAC.
     * 
     * @param key
     * @param hmacInput
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private byte[] generarHMAC(SecretKey key, byte[] hmacInput) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance(ALGORITHM_HMAC);
        hmac.init(key);
        return hmac.doFinal(hmacInput);
    }
	
    /**
     * 
     * Get HMAC	length.
     * 
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
	private int obtenerHMACLength(SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance(ALGORITHM_HMAC);
        hmac.init(key);
        return hmac.getMacLength();
   }
	
	/**
	 * 
	 * Generate map of fields to encrypt.
	 * 
	 * @param camposCifrar
	 * @return
	 */
	public Map<String, String> generarMapCamposCifrar (String camposCifrar) {
		 
        Map<String, String> camposCifrarMap1 = new HashMap<>();
        if (camposCifrar != null) {
        	String camposCifrarArray [] = camposCifrar.split(",");
            for (int i = 0; i < camposCifrarArray.length; i++) {
            	camposCifrarMap1.put(camposCifrarArray[i].trim(), camposCifrarArray[i].trim());
    		}
        }
        
        return camposCifrarMap1;
	}
	
	/**
	 * Validate if the field should be encrypted.
	 * 
	 * @param camposCifrarMap
	 * @param jsonPathResponse
	 * @return
	 */
	private boolean validarCampoCifrar (Map<String, String> camposCifrarMap1, String jsonPathResponse) {
		
		String campo = camposCifrarMap1.get(jsonPathResponse);
        
        if (campo != null && campo.equals(jsonPathResponse.toString())) {
        	return true;
        } else {
        	return false;
        }
		
	}

	public String getCamposCifrarResponse() {
		return camposCifrarResponse;
	}
	
	public void setCamposCifrarAesResponse(String camposCifrarAesResponse) {
		this.camposCifrarAesResponse = camposCifrarAesResponse;
	}

	public void setCamposCifrarResponse(String camposCifrarResponse) {
		this.camposCifrarResponse = camposCifrarResponse;
	}

	public String getAlgoritnoTipoCifrado() {
		return algoritnoTipoCifrado;
	}

	public void setAlgoritnoTipoCifrado(String algoritnoTipoCifrado) {
		this.algoritnoTipoCifrado = algoritnoTipoCifrado;
	}
    
	public void setCamposCifrarMap(Map<String, String> camposCifrarMap) {
		this.camposCifrarMap = camposCifrarMap;
	}
	
	public void setCamposCifrarAesMap(Map<String, String> camposCifrarMap) {
		this.camposCifrarAesMap = camposCifrarMap;
	}
}
