package com.cifrado.descifrado;

import java.util.Map;


public class CifradoDescifradoApplication {
	static CifradoDescifrado cifradoDescifrado = new CifradoDescifrado();
	protected static long startTime;
	protected static long elapsedTime;

	public static void main(String[] args) throws Exception {
		// "folio": "99555187|1-1-8757-17872|1-8757-17872-8"
		startTime = System.currentTimeMillis();
		Llavero llavero = cifradoDescifrado.extraerLlaves("{\n" + 
				"    \"idAcceso\": \"G797283220210223182624\",\n" + 
				"    \"accesoPublicoBackend\": \"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoWVmKvFDZ2enNiFaOVe+zSjQxj5lAi7xCYdRgQ8sqIG08+3ApiZWNHF6z/9TjmK3iD4Ta7RAwvMUF8wsTf5rajM9GnC0R2SbNyVJlzNXXVcd3p3d24PsCs7fwDNPWatDBvoUVKsCfdnqzp2E6J52RXkVeEvEPibBrOCJl15SrbuHhHAExT23nOqXWBTXYStUWQsQeKqRntg1bM6eYM5IYVRGrHK1NUs+QuAAyPMCZWRcaQSdFR/CkcE3f4rhuOJNUL3ZDMvi3yi3AMaaGgYyFJv4St/SRkIdStGOjNey5Cmg2AY6G2bej9jCkAWSNs+SURhjeASWkyyEaqg9IDrhVQIDAQAB\",\n" + 
				"    \"accesoPrivadoCliente\": \"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCecrh+v9fGJvXUf8qFtYejh2js50VVWa3uufMBpl5u8XQ6iIFAmK50NTCz+shgvDG3P2Ms01rP6hA0IhFJzYKI1cctUbTnZKiXQxQk5wSQteFdOoVsRsuqNJH2Z/Ca4yANEQGur9/WXIcQYwYiRith7LmGr6PXJ4D4FjnWTprjeKoe5DJOot8dY9jRhL7/pZVqOIe4GhGlALii1vPtgeUHmbxHBMHSBQeSvBGDO9i0vcz1ozXQN9/Ss4WTC02UOVa2TGT0Zm25co6/Ugo9OG+3dAzCawTuOz1dhd9YzRGqZr4NGuLTRcGLbMlfUPuX9mUk99kJp0PgfIwLKhOcMYrbAgMBAAECggEAArF8hVsJderPnu8VKDSvu43fsLc1rH+7vyUWBl8nl8cu7JfJaqmtvXfx/f0zTsGkLrM7IlXtYz9dSyWRGaoOn/jSS+jppE1HuFqBiycKItOUoxsNjYuehqQiHwghROC7eL1jQ+7lZSVpflPQlOCQeDen56wwosWfIZhvHz9yzo8FqkqtuGgrZsZRx8n9ibJYAATFfRMGCZksHFAr1cHlh2RIxpLegzx60/bWVD1iAa9V2UyIShDIlakA6HJ2KbyBQgNYMMa5f8HaFBKAuJ06onb7gdMRepb4LFU9KjcqcN0rs9NNwYz8vyUKunMB1lJnvF7Ml0awxu6dWTTNW+xTcQKBgQDToUSWuw8VMC5ys2GX7B1GL4wdk/al9NN0Kfcvtzka1pHitKp2jqudPXMH0+Hcxl5tVez9XOIMSaHEsc4Ojy2KkMiqrR0twFP5ifwApdnVua8N8GsA0VhqjwoGNOboIx397xL4AIVEh5kU7ZMZR6l0FcgJD25WRCCGBmMIbj8xLwKBgQC/qwuctLG1BwK2+7g4J1Ka+xIZvuhV8Z9Q/+7mPT12tPrxRiPPmufgzyhWpq9p/bDUrWmPo1jk5gETvCf20PUMX0lPn1HrS4m1ZeuvtiytXXSSKwf9TB9Q5g8Jhi21s37ai6UyyO3rv9qgzFg+2TUHZtkvjtcFIyncsgOU5PEeFQKBgQC4lsJnorNOLN9mpnXqeEU0ccaKCgQpzrkdankzgmHpudw3XJQOovSnSvDpk/M+iTvVrAvxFjIIu7TBBsGPS21SnmSZEjQQMuBMGQHYYtD1LWe2BcyfQvoWv7rjnQMG2ZKQahYmgPW8x3VLnskeVLZGDbWTyYTY88VWgX2h3d96ewKBgAXQZnXGdW2MXOS4Fgp+Bt90pkPpqpqQzchjOxf425hyPPp8qjFt/YKwRgT64W9Rpr+M0xrsVZH7+CRlHME77XMGoO6tYorEq9S41sFpwUkxAIw+ieuBF6IYmHQtgc11OG/gtI4p0i3IkBU4naSqahVjZUswBg2911M+gyVNHJNRAoGBALaq05ZMUZNT/zJqBCB6x3lraUs4qDy4HsxtsuHNX+13Py7tUzlIknly/sC5p3yL+TpMZVXruL/cvcLEsoJAKAHDCRl7W9hrmjeos+sLnVl+uXOgkoAxnJ6GwL+InhVEYZg8mmhxFo5ipWTJlNVy1IFkhl8wNvfSqM1m4jNlOj20\",\n" + 
				"    \"accesoSimetrico\": \"vh/5pBnd8cE4DYXwNALyUA==\",\n" + 
				"    \"fechaHoraExpiracion\": \"vh/5pBnd8cE4DYXwNALyUA==\",\n" + 
				"    \"codigoAutentificacionHash\": \"dubSvIxpvADlRRuqVahfp06xQuvqOUW/vsb+qPu36DE=\"\n" + 
				"}");
		
		System.out.println("identificador de acceso: " + llavero.getIdAcceso());
    	System.out.println("acceso privado cliente: " + llavero.getAccesoPrivadoCliente());
    	System.out.println("acceso publico backend: " + llavero.getAccesoPublicoBackend());
    	System.out.println("acceso simetrico: " + llavero.getAccesoSimetrico());
    	System.out.println("codigo autentificacion Hash: " + llavero.getCodigoAutentificacionHash());
    	
    	//cifradoDescifrado.setCamposCifrarResponse("$.lista[*].test2.test3.test4.lista[*].descripcion, $.lista[*].test2.test3.test4.lista[*].nombre, $.nombre");
    	//clientes POST /
    	String camposCifrar = "$.nombre, $.apellidoPaterno, $.apellidoMaterno, $.fechaNacimiento, $.idNacionalidad, $.idGenero, $.idEntidadFederativaNacimiento, $.contacto.correo, $.contacto.celular,"
    			+ " $.domicilio.calle, $.domicilio.codigoPostal, $.domicilio.idEntidadFederativa, $.domicilio.idMunicipio, $.domicilio.municipio, $.domicilio.idColonia, $.domicilio.colonia";
    	
    	//cuentas POST ​/nivel-2 {\"POST/nivel-2\": \"$.resultado.numeroCliente, $.resultado.numeroCuenta, $.resultado.cuentaClabe\"}
    	//camposCifrar = "";
    	
    	
    	//cuentas POST ​/vincula-tarjetas {\"POST/vincula-tarjetas\": \"\"}
    	//camposCifrar = "$.numeroCliente, $.numeroCuenta, $.numeroTarjeta, $.nip";
    	
    	//cuentas POST ​/saldos {\"POST/saldos\": \"$.resultado.saldoDisponible, $.resultado.titularCuenta, $.resultado.numeroCuenta\"}
    	//camposCifrar = "$.numeroCuenta";
    	
    	//cuentas POST ​/saldos-acumulados {\"POST/saldos-acumulados\": \"$.resultado.saldoAcumulado.montoDepositos, $.resultado.saldoAcumulado.montoRetiros, $.resultado.saldoAcumulado.montoTotalDepositos, $.resultado.saldoAcumulado.montoTotalRetiros\"}
    	//camposCifrar = "$.numeroCuenta, $.monto";
    	
    	//cuentas POST /movimientos/busquedas {\"POST/movimientos/busquedas\": \"$.resultado.numeroCuenta, $.resultado.titularCuenta\"}
    	camposCifrar = "{\"POST/validacion-operaciones\": \"$.nombre, $.resultado.bloqueo.nivelRiesgo, $.apellidoPaterno, $.resultado.nombre, $.resultado.cuestionarios[*].preguntas.respuestas[*].valor, $.resultado.cuestionarios[*].valor, $.resultado.bloqueo.temporal[*], $.temporal[*]\"}";
    	
    	camposCifrar = "$.nombre, $.resultado.bloqueo.nivelRiesgo, $.apellidoPaterno, $.resultado.nombre, $.resultado.cuestionarios[*].preguntas.respuestas[*].valor, $.resultado.cuestionarios[*].valor, $.resultado.bloqueo.temporal[*], $.temporal[*], $.lista[*].test2.test3.test4.temporal[*]";
    	
    	String camposCifrarAes ="$.imagen  ,    $.resultado.documento";
    	cifradoDescifrado.setCamposCifrarResponse(camposCifrar);
    	cifradoDescifrado.setCamposCifrarMap(cifradoDescifrado.generarMapCamposCifrar(camposCifrar));
    	
    	cifradoDescifrado.setCamposCifrarAesResponse(camposCifrarAes);
    	cifradoDescifrado.setCamposCifrarAesMap(cifradoDescifrado.generarMapCamposCifrar(camposCifrarAes));
    	
    	cifradoDescifrado.setAlgoritnoTipoCifrado("RSA/ECB/PKCS1Padding"); // RSA/ECB/PKCS1Padding, RSA/ECB/OAEPWithSHA-1AndMGF1Padding , RSA
    	
		Map<String, Object> objetoRequestResponse2 = cifradoDescifrado.convertirRequestResponseObjeto("{\n" + 
				"    \"nombre\": \"JOSE Pérez\",\n" + 
				"    \"imagen\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wC ......\",\n" +
				"    \"imagen2\": \"W1G9v5wN9lFirIdv0H9vQ84mTS2Xny3HTvNQhKOi2zervC1+UEg0vvBV6b/5tl4A7ry8bLvZiH1SuQwdzBpCLf5LKIB9lUQgSJDtD1SRrQcjZqwtRHtNFCMs5hKq4Q3U\",\n" +
				"    \"apellidoPaterno\": \"null\",\n" + 
				"    \"apellidoMaterno\": \"LOPEZ\",\n" + 
				"    \"fechaNacimiento\": \"24/07/1987\",\n" + 
				"    \"idNacionalidad\": \"449\",\n" + 
				"    \"resultado\": {\n" + 
				"        \"documento\": \"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><TICKET><TEXT /><TEXT /><IMG PATH=\\\"\\\\\\\\10.54.66.20\\\\adn\\\\BMP\\\\Bancoazteca_logo.gif\\\" POSX=\\\"20\\\" WIDTH=\\\"30\\\" HEIGHT=\\\"12\\\" /><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"10\\\" FONTTYPE=\\\"B\\\">TABLA DE AMORTIZACIÓN</TEXT><TEXT /><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"8\\\" FONTTYPE=\\\"B\\\">INFORMACIÓN DEL ACREDITADO</TEXT><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\">Nombre: CARLOS FUENTES GARCIA</TEXT><TEXT FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\">Número de cuenta: 14624237373</TEXT><TEXT FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\">Número de cliente único: 0101-4624-1467</TEXT><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"8\\\" FONTTYPE=\\\"B\\\">INFORMACIÓN DEL CRÉDITO</TEXT><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TABLE><ROW><HEADER ALIGN=\\\"LEFT\\\">Número de pedido:</HEADER><HEADER ALIGN=\\\"RIGHT\\\" FONTTYPE=\\\"B\\\">#1194602</HEADER><HEADER ALIGN=\\\"LEFT\\\">Plazo semanal:</HEADER><HEADER ALIGN=\\\"RIGHT\\\">102</HEADER></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Monto del crédito:</CELL><CELL ALIGN=\\\"RIGHT\\\">$4,230</CELL><CELL ALIGN=\\\"LEFT \\\">Capital semanal:</CELL><CELL ALIGN=\\\"RIGHT\\\">$21</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Descuentos:</CELL><CELL ALIGN=\\\"RIGHT\\\">$699</CELL><CELL ALIGN=\\\"LEFT\\\">Prima de seguro:</CELL><CELL ALIGN=\\\"RIGHT\\\">$0</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Enganche:</CELL><CELL ALIGN=\\\"RIGHT\\\">$2,099</CELL><CELL ALIGN=\\\"LEFT\\\">Comisiones:</CELL><CELL ALIGN=\\\"RIGHT\\\">$0</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Interés Ordinario</CELL><CELL ALIGN=\\\"RIGHT\\\" /><CELL ALIGN=\\\"LEFT\\\" /><CELL ALIGN=\\\"RIGHT\\\" /></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Anual:</CELL><CELL ALIGN=\\\"RIGHT\\\">$2,735</CELL><CELL ALIGN=\\\"LEFT\\\">IVA de Comisiónes:</CELL><CELL ALIGN=\\\"RIGHT\\\">$0</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">IVA interés</CELL><CELL ALIGN=\\\"RIGHT\\\" /><CELL ALIGN=\\\"LEFT\\\" /><CELL ALIGN=\\\"RIGHT\\\" /></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Ordinario Anual:</CELL><CELL ALIGN=\\\"RIGHT\\\">$438</CELL><CELL ALIGN=\\\"LEFT\\\">Comisión:</CELL><CELL ALIGN=\\\"RIGHT\\\">$0</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Interés Ordinario</CELL><CELL ALIGN=\\\"RIGHT\\\" /><CELL ALIGN=\\\"LEFT\\\" /><CELL ALIGN=\\\"RIGHT\\\" /></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Semanal:</CELL><CELL ALIGN=\\\"RIGHT\\\">$27</CELL><CELL ALIGN=\\\"LEFT\\\">IVA de comisión:</CELL><CELL ALIGN=\\\"RIGHT\\\">$0</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">IVA Interés</CELL><CELL ALIGN=\\\"RIGHT\\\" /><CELL ALIGN=\\\"LEFT\\\" /><CELL ALIGN=\\\"RIGHT\\\" /></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Ordinario Semanal:</CELL><CELL ALIGN=\\\"RIGHT\\\" /><CELL ALIGN=\\\"LEFT\\\">101 pagos de</CELL><CELL ALIGN=\\\"RIGHT\\\">$52</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Pago Semanal:</CELL><CELL ALIGN=\\\"RIGHT\\\">$52</CELL><CELL ALIGN=\\\"LEFT\\\">último pago Semanal:</CELL><CELL ALIGN=\\\"RIGHT\\\">$52</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">Saldo a la fecha:</CELL><CELL ALIGN=\\\"RIGHT\\\">$5,304</CELL><CELL ALIGN=\\\"LEFT\\\">Saldo del periodo:</CELL><CELL ALIGN=\\\"RIGHT\\\">$5,304</CELL></ROW></TABLE><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT>LISTA DE CÓDIGOS</TEXT><TABLE><ROW><HEADER ALIGN=\\\"CENTER\\\" FONTTYPE=\\\"B\\\">CÓDIGO</HEADER><HEADER ALIGN=\\\"CENTER\\\" FONTTYPE=\\\"B\\\">DESCRIPCIÓN</HEADER><HEADER ALIGN=\\\"CENTER\\\" FONTTYPE=\\\"B\\\">CANTIDAD</HEADER></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">101552</CELL><CELL ALIGN=\\\"LEFT\\\">LICUADORA OSTER 6630 6640 8/10</CELL><CELL ALIGN=\\\"RIGHT\\\">1</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">847611</CELL><CELL ALIGN=\\\"LEFT\\\">GTIA. EXT. MILENIA 4+1 MX</CELL><CELL ALIGN=\\\"RIGHT\\\">1</CELL></ROW><ROW><CELL ALIGN=\\\"LEFT\\\">1009105</CELL><CELL ALIGN=\\\"LEFT\\\">LED 24 HKPRO HKP24F01</CELL><CELL ALIGN=\\\"RIGHT\\\">1</CELL></ROW></TABLE><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.8\\\" /><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"10\\\" FONTTYPE=\\\"B\\\">Plazo del Crédito</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"10\\\" FONTTYPE=\\\"B\\\">Sus pagos son el día: SABADO</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"10\\\" FONTTYPE=\\\"B\\\">Del 27/03/2021 al 04/03/2023</TEXT><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"11\\\" FONTTYPE=\\\"B\\\">Ahorra $816 pagando un total</TEXT><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"11\\\" FONTTYPE=\\\"B\\\">de $4,488 con pagos puntuales</TEXT><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"11\\\" FONTTYPE=\\\"B\\\">de $44 en 102 semanas</TEXT><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"6\\\" FONTTYPE=\\\"B\\\">Reglas para Pago Puntual:</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"6\\\" FONTTYPE=\\\"B\\\">1) El descuento por pago puntual aplica solo para</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"6\\\" FONTTYPE=\\\"B\\\">Acreditados que se encuentren al corriente en sus pagos.</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"6\\\" FONTTYPE=\\\"B\\\">2) Si el Acreditado se atrasa y se vuelve a poner</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"6\\\" FONTTYPE=\\\"B\\\">al corriente regresa la promoción.</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">* Por política de venta y por naturaleza del producto le.</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">informamosquenose aceptan devolucionesdelas</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">Italikauna vezquehasidoentregada y rodada.</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">* Por naturalezadel productono haycancelaciones ni</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">cambios físicos de la unidad.</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">* Por cada serviciola unidaddebe ser llevada al taller</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">autorizado y recibir el sello en el cupón correspondiente,</TEXT><TEXT ALIGNH=\\\"LEFT\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"7\\\" FONTTYPE=\\\"N\\\">de no ser así perderá su garantía.</TEXT><TEXT /><TEXT /><TEXT /><TEXT /><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><LINE POSX=\\\"0\\\" WIDTH=\\\"70\\\" BORDERWIDTH=\\\"0.5\\\" /><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"9\\\">4624 MEGA VILLA OLIMPICA</TEXT><TEXT ALIGNH=\\\"CENTER\\\" FONTFAMILY=\\\"TAHOMA\\\" FONTSIZE=\\\"9\\\">Fecha: 19/03/21 Hora: 08:47 a.m.</TEXT></TICKET>\"\n" + 
				"    }, "+  
				"    \"idGenero\": \"M\",\n" + 
				"    \"idEntidadFederativaNacimiento\": \"13\",\n" + 
				"    \"contacto\": {\n" + 
				"        \"correo\": \"herlopezderff@gmail.com\",\n" + 
				"        \"celular\": {\n" + 
				"            \"numero\": 34343434,\n" + 
				"            \"primario\": true,\n" + 
				"            \"nombre\": \"casa\"\n" + 
				"        },\n" + 
				"        \"telefono\": {\n" + 
				"            \"numero\": 34343434,\n" + 
				"            \"primario\": true,\n" + 
				"            \"nombre\": \"casa\",\n" + 
				"            \"test1\": {\n" + 
				"                \"test1.1\": \"test1.1\",\n" + 
				"                \"test1.2\": \"test1.2\",\n" + 
				"                \"lista\": [\n" + 
				"                    {\n" + 
				"                        \"nombre\": \"Item 1\",\n" + 
				"                        \"id\": 1,\n" + 
				"                        \"descripcion\": \"Test item 1\"\n" + 
				"                    },\n" + 
				"                    {\n" + 
				"                        \"nombre\": \"Item 2\",\n" + 
				"                        \"id\": 2,\n" + 
				"                        \"descripcion\": \"Test item 2\"\n" + 
				"                    }\n" + 
				"                ],\n" + 
				"                \"test2\": {\n" + 
				"                    \"test2.1\": \"test2.1\",\n" + 
				"                    \"test2.2\": \"test2.2\",\n" + 
				"                    \"test3\": {\n" + 
				"                        \"test3.1\": \"test3.1\",\n" + 
				"                        \"test3.2\": \"test3.2\",\n" + 
				"                        \"test4\": {\n" + 
				"                            \"test4.1\": \"test4.1\",\n" + 
				"                            \"test4.2\": \"test4.2\",\n" + 
				"                            \"lista\": [\n" + 
				"                                {\n" + 
				"                                    \"nombre\": \"Item 1\",\n" + 
				"                                    \"id\": 1,\n" + 
				"                                    \"descripcion\": \"Test item 1\"\n" + 
				"                                },\n" + 
				"                                {\n" + 
				"                                    \"nombre\": \"Item 2\",\n" + 
				"                                    \"id\": 2,\n" + 
				"                                    \"descripcion\": \"Test item 2\"\n" + 
				"                                }\n" + 
				"                            ]\n" + 
				"                        }\n" + 
				"                    }\n" + 
				"                }\n" + 
				"            }\n" + 
				"        }\n" + 
				"    },\n" + 
				"    \"lista\": [\n" + 
				"        {\n" + 
				"            \"nombre\": \"Item 1\",\n" + 
				"            \"id\": 1,\n" + 
				"            \"descripcion\": \"Test item 1\",\n" + 
				"            \"test2\": {\n" + 
				"                \"test2.1\": \"test2.1\",\n" + 
				"                \"test2.2\": \"test2.2\",\n" + 
				"                \"test3\": {\n" + 
				"                    \"test3.1\": \"test3.1\",\n" + 
				"                    \"test3.2\": \"test3.2\",\n" + 
				"                    \"test4\": {\n" + 
				"                        \"test4.1\": \"test4.1\",\n" + 
				"                        \"test4.2\": \"test4.2\",\n" + 
				"                        \"lista\": [\n" + 
				"                            {\n" + 
				"                                \"nombre\": \"Item 1\",\n" + 
				"                                \"id\": 1,\n" + 
				"                                \"descripcion\": \"Test item 1\"\n" + 
				"                            },\n" + 
				"                            {\n" + 
				"                                \"nombre\": \"Item 2\",\n" + 
				"                                \"id\": 2,\n" + 
				"                                \"descripcion\": \"Test item 2\"\n" + 
				"                            }\n" + 
				"                        ]\n" + 
				"                    }\n" + 
				"                }\n" + 
				"            }\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"nombre\": \"Item 2\",\n" + 
				"            \"id\": 2,\n" + 
				"            \"descripcion\": \"Test item 2\",\n" + 
				"            \"test2\": {\n" + 
				"                \"test2.1\": \"test2.1\",\n" + 
				"                \"test2.2\": \"test2.2\",\n" + 
				"                \"test3\": {\n" + 
				"                    \"test3.1\": \"test3.1\",\n" + 
				"                    \"test3.2\": \"test3.2\",\n" + 
				"                    \"test4\": {\n" + 
				"                        \"test4.1\": \"test4.1\",\n" + 
				"                        \"test4.2\": \"test4.2\",\n" + 
				"                        \"lista\": [\n" + 
				"                            {\n" + 
				"                                \"nombre\": \"Item 1\",\n" + 
				"                                \"id\": 1,\n" + 
				"                                \"descripcion\": \"Test item 1\"\n" + 
				"                            },\n" + 
				"                            {\n" + 
				"                                \"nombre\": \"Item 2\",\n" + 
				"                                \"id\": 2,\n" + 
				"                                \"descripcion\": \"Test item 2\"\n" + 
				"                            }\n" + 
				"                        ],\n" + 
				"            \"temporal\": [\n" + 
    			"                \"ACUM\",\n" + 
    			"                \"NUM_ENV\"\n" + 
    			"            ]\n" +
				"                    }\n" + 
				"                }\n" + 
				"            }\n" + 
				"        }\n" + 
				"    ],\n" + 
				"            \"temporal\": [\n" + 
    			"                \"ACUM\",\n" + 
    			"                \"NUM_ENV\"\n" + 
    			"            ]\n" +
				"}");
		
		startTime = System.currentTimeMillis();
		System.out.println("inicia recorrido de mapa para cifrar request restructura");
		cifradoDescifrado.cifrarCamposResponse(objetoRequestResponse2, llavero, "");
		
		startTime = System.currentTimeMillis();
		System.out.println(cifradoDescifrado.convertirRequestResponseJson(objetoRequestResponse2));
		elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo de ejecusion: " + elapsedTime + " ms");
		
        startTime = System.currentTimeMillis();
		System.out.println("inicia recorrido de mapa para descifrar request restructura");
		
		llavero = cifradoDescifrado.extraerLlaves("{\n" + 
				"    \"idAcceso\": \"G797283220210223182624\",\n" + 
				"    \"accesoPublicoBackend\": \"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnnK4fr/Xxib11H/KhbWHo4do7OdFVVmt7rnzAaZebvF0OoiBQJiudDUws/rIYLwxtz9jLNNaz+oQNCIRSc2CiNXHLVG052Sol0MUJOcEkLXhXTqFbEbLqjSR9mfwmuMgDREBrq/f1lyHEGMGIkYrYey5hq+j1yeA+BY51k6a43iqHuQyTqLfHWPY0YS+/6WVajiHuBoRpQC4otbz7YHlB5m8RwTB0gUHkrwRgzvYtL3M9aM10Dff0rOFkwtNlDlWtkxk9GZtuXKOv1IKPThvt3QMwmsE7js9XYXfWM0Rqma+DRri00XBi2zJX1D7l/ZlJPfZCadD4HyMCyoTnDGK2wIDAQAB\",\n" + 
				"    \"accesoPrivadoCliente\": \"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChZWYq8UNnZ6c2IVo5V77NKNDGPmUCLvEJh1GBDyyogbTz7cCmJlY0cXrP/1OOYreIPhNrtEDC8xQXzCxN/mtqMz0acLRHZJs3JUmXM1ddVx3end3bg+wKzt/AM09Zq0MG+hRUqwJ92erOnYTonnZFeRV4S8Q+JsGs4ImXXlKtu4eEcATFPbec6pdYFNdhK1RZCxB4qpGe2DVszp5gzkhhVEascrU1Sz5C4ADI8wJlZFxpBJ0VH8KRwTd/iuG44k1QvdkMy+LfKLcAxpoaBjIUm/hK39JGQh1K0Y6M17LkKaDYBjobZt6P2MKQBZI2z5JRGGN4BJaTLIRqqD0gOuFVAgMBAAECggEBAJOZBawz466HLnXkXnyecBFzSCpkGT+76hcKt1DNrcuUgwWo4kuQN7nSJbxeBe3q/WkNS/ltaORgJHn28HWLPHRs026Ar32y1ffCk+BAxZ7I8roJqUD9Djp8IkfFJMYeRzf0L++WTLsHNSMrDsclU4gJSccvkxI7o1cUKQBS9E2H/0A5p5u7RAFquMll2vdHew1SQ5hfPdCQYKeBkPr4vlE/AlEh8i5zBmbLToq5R9UyuERmh6NHYxhxvdq4ZY2dIiL5yMjyyGiTPLS6N86lTdWBmmy3f+LpoenXDwHejI/f09Zc6+k5kcOeL4K/cRvb3NUwOZWAYwXj1gIHacGQ40ECgYEA8IpHDHnw+onTQ0tAQxJ8UTIZbZ19EqHUw+fZgAfIbQEhV4EvwNH80Jm8ZGIgiA1BCZ7VnCwBerNHTsIfkmo8e8ey2rIEfYs8iKW4mZXBgYWNonP/+HOWh8bLcN5jDPPa+ujKOIwf2iUrkJpMspM4jwEm+5+hI1eMwqj9jvFaEUkCgYEAq8TtKbdprrYZs8vpU/v6xJCNUtENDvoVHPbDRzbAKOsOLT+XlPSUMXX6WjZiumMYj9TTDtg5lOqpwXVeZFtA/V4bhlfb726uD7i69uKkxrlGzZTZFRuTcoKGVrVyDKM5eNP9ByVgGl//k5Cqh+zdg10CRASqYwAjKV0CnJS2m60CgYB3tOks3Va+6G5Z9A40EvQJWHxtH85Jwn3nTO/iFn15OfHE4YbJV/eQPKIT9v2nl6kBR6mYwhGuZaAcIuGhdPtKwpRQ3/z6GZvziT24OV4GvldBPKdrluKYCaffMN1sacNV0RoqUBnd71eh7Bg1WqzwBivHV6bSttRXX9BoHdar+QKBgQCUu9RQhwzvx6bB1ROZMVuW0UROLc6M5WdaySGWjIYf6ElCTQpIZO/dVDcP3n/mvOALy1FmTTyvAfM0HRk69Pl1awOSdIlw7fmyi1b5aI8dtFjmsri2WkZ2pScF9d9nqf6d7ZHvmiF5NR7s/Bttz2CP2v2XQCL5zqijrHISe4MTkQKBgD5+/vXyjcKaiUKyxwtspwfiC1vVmLNGwmw7AUQrrDV6OBvR6IoU1kYFfnysh7lxTx+5gJNhvk1Lvgn4mdUH8j4orSYoAXPKvjecODz+u1wNRp4CpRyJd8O34uoSRDvUJFc4HGwhLFRSRQz8ElHJL4v2FQj7dfKTKPLchQb09N1T\",\n" + 
				"    \"accesoSimetrico\": \"vh/5pBnd8cE4DYXwNALyUA==\",\n" + 
				"    \"codigoAutentificacionHash\": \"dubSvIxpvADlRRuqVahfp06xQuvqOUW/vsb+qPu36DE=\"\n" + 
				"}");
		
		cifradoDescifrado.descifrarCamposRequest(objetoRequestResponse2, llavero);
		System.out.println(cifradoDescifrado.convertirRequestResponseJson(objetoRequestResponse2));
		
        
        String imagen= "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";
        String simetrica= "2P8/odYiB4lIvU7Ax/it0A==";
        String hmac= "P36AuPQF2LaA0YnahVszQRaONVV0JmHlwztnQNPBVOI=";
        //imagen = "prueba cifrado";
        String cifrado = cifradoDescifrado.encryptAes( simetrica, hmac, imagen);
        System.out.println("cifrado aes: "+cifrado);
        
        cifrado = "SzdIBEbWX65IsPb/j2ZZ4YPzY+3qMCGF6IeRJWwwauDpExBY6DF3M/xtq3xZDU+uFaRlmgn6ndHZEJ5z5EH3TEeKHUCmW5g3TkoPWlwS2ampkOPCB61TkrqVuIbZ/1yj3kgZaYa2n2iCUoqxTSCEyfy0I37qvmsQSAindqTJD9LBNT3/muww/H2ZNEEv6S49N5wPoQtnMZ57o1L3gSOCnA95YYEn5UpuMwukX3sH87Y5sWp71izP2KWZwwpuRbWoTHjtEAK9hBlzgsbI6wPEU8dfR/MXOlAJNGSku6w1kzR7BLNQm+fKYvo7cRcKXuFjZJoYxBBo1VqLNL/ae/Sbe27t98IGAtNYdFErnZxElrYQWCp9qp+tCQ3yI2965cowElH/sK48XHHC3Tavsk2NLj+UpPcggWEVbtidwfkm0GcfNFY4Up9qKddu0UDYJfGDsNYq6THB7EddmSvCUOXTwh0io4zL+n9HJuhZhVSseXBUxfh4ywCe6waHihsyM/LRqUR/gqngEbw7k4Kq4X7MVDClZ8I55KyasoSxaYLDq/75jlqo5utaVo9YJ/35C64eJ3D6j3lsUBGghM4I/FNwcFjoiVPLuVq+fsEeG/dnmrEUTExMZw4M4fOF5vHp07d+StLjSVrEvaf3IsIQO0VGSzSxIZvfpJy6o197kyxKnbN7Xk7MdP/oTm2k380znreYCyuw4L+JVk/a1vD6GvqIGbVdTgivy6y9fAhMF2SCUnLWMYYWiugasB8PGy4KSPKrCxjIpjgOrETnkZJcMS/iMmH2I44Dc56GvBnWA/IDXtJUKWf+gkL59jPaHMlpHvLs/6hiKIGr5Igz78uasju4VnUjy0BByA64a9iF2G2mUKT48JgKbhwFoWm0Ievt6v/kBkbxl3zvs/ux/NpNF/dTP+0UXUphvfjAPiTSn8pWMId1pjpMvSe/SjmtzFDZfcwdyT3GbHoFA3X9yL3sn4fS6EAImPPo+pt0WmaCD20djIjc3jpbU6MrWI/F1bk4K/KLSzoz3qfBPzbV70+ybhmeXqG6mQAz9MxAAuKy14Sz6PA=";
        simetrica = "2P8/odYiB4lIvU7Ax/it0A==";
        hmac = "P36AuPQF2LaA0YnahVszQRaONVV0JmHlwztnQNPBVOI=";
        
        String descifrado = cifradoDescifrado.decryptAes( simetrica, hmac, cifrado );
        
        System.out.println("descifrado aes: "+ descifrado);
        
        String valorCampo = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDQT3r4tYqJUoEYbd4+aTSd4JQ2uGJ06PnwZWs/cNS4zJ95xaROgX2EMfBWYAk1j7Trhr2u3YMpMg00Nyh8Rz6mrWPrGewIPDBEMqM53gIULWsryFdqPnmSp07p4qePgX3oJcTvH7JfSjH3b9ghC2h0tPIJ0a5YciQDDfzT/I5IK+AHhmJjrFKTBSEQ6vP5sXWN+thuwym7Vtg1s8MoItbU38h1rf7iroq3rWAvzEm44zHmQVAouqX2D4WSSgvTY4IK7QPAcCnjlTVJ+wBw3GVBxsYSRIkPAdvLOkP0wfbZPy4+vF16ooeL2gxuSXXPETIrwn73igadz37BrCcVzXBPAgMBAAECggEAK9TtjTO16NtBasCDiSJd/fJpfXIi1XLIVLsibADz4ca8r8TUo+g/XvebGLGwfmK0sP";
        System.out.println("longitud cadena: " + valorCampo.getBytes("UTF-8").length);
        String stringPublicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAkMBUDWolswSLMu2H3qJxwzsSMC6RDoRdHexHAFf1TsCUys/UzvGs99Jjv5K0j8DHrWz/2reAesxgDmu2oPfOtrZq7Tf1N7k69oJdfyp4gBxVAEX4YD33eDIaPFMY3aWpyIHkiBnFSvyuW13p92KJACBMMWv6SWxdCes35UK8lHh9zel637cvWQmeF6wwso6jfmGQ4i4UPN8cqbDnDORMsQnba6FqBjcU1+35TIGEVRtOVqONIKVQWQUfgKIiRQYosmIUoFKYIgLAjl+YfiCeA8kc1F5ZpinT1UE/CJtI4VinrL2eQjj6jiKMtVq0U+1P6p3lxACPwQo50LDwySFaFWi4w8caqV3/uJwicfs9YXiqqhWnr35nwv0JfL7oYjviczKCmdXl/KeewibGdeMX/tSRP4zCuIDQ28z4jVJ34n0Gm2Eao7f17BjN9fo6tqthv9KhIpPyKql3kJsRYOk3LUsMA05n5w/aOh5F8r5EBLff3thzey4EI+Fxv68EAytF2rqyi+LPAXG0btOs+Pu2IsFTRB57d7KAANIxLvw5FvefF1KwoUXkegL1Z77tzLoJIQlRd320NG8q3wfI/veSzIco6lbXh7xNKd8agC9LVs4WW9BLIbu5dqyBNGoXpsR6x6CEi2IOR8aJNqEMRhF6AhOSj5tA4ear+M6D4Bbu9CsCAwEAAQ==";
        System.out.println("cifrado llave privada con rsa: "+cifradoDescifrado.encriptarRsa(valorCampo, stringPublicKey));
        
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Tiempo de ejecusion: " + elapsedTime + " ms");
        
        System.out.println(cifradoDescifrado.desencriptarRsa("C6aL54bUzL/E5c+ilKdUMIY+D3R+KwlDACq5Us5vB3LlHCy+NqP43oCSbWJ8lYbrCSktAmePHvSdhKxjpK7+5EHAWrmb3UrAXC4D5b2rogm0U/KW9LBt49TMb2pzcxc7kplluyjyMqtmJg3FwqHt6m4AG0FQSA5Yj3DUzSVqjNmcW2Db+tK0BzIswNDZX6/K+ftMDx7y+n1SZOFq8SK5+UnUgkQCuTPn/ld4Uiz7xqgbJRCLDxxx5MVFq4cyA+CNEC4KN1NwHHCGREybo1lLKjTfjqrXNfLg2VJDtarxU0BP+qJx1hPrz6WagyugcvcJTkHlD5OG7vH4RNGl7zr28g==", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIHR/cFOli6AFE9IiyJ1izueQUAJn32Yb/d1KI/EpB3WyC7DUPJHGkNOOLMGjuOm2B289cTSlZFZtvMrN76LRFAkHyZgwqF4jlPQWSwOUQE6layFTMujs6ojYFlRCPz0rcSP5ofScvTG9KSeZhl3Xf538TPQNMaEDroLnTnLm3JK5vAGYdO0PclwbDASQyXxjUoKNOjTosV7HPrTb2bxQPU16ElgScW5MDDYRLxT9Tbe8vlFN2h738i6vY6PSOlHNT+/DSsLQ6ECl0HEis6j5b7xPtC9jjcmknKWJ2ivM979QB0W59IB1tTZGeYBAlzGN+rP5mm+KIwCJfEjWxXBlbAgMBAAECggEAYbuP0cXOIm914d43fLwVy87Q/q/D3hv5+9rgj22foBVuQb97BalkU8SKEborzcp0MMb06FCbPNTv63MbPwBC2D4bvgIZ/+swJ3GeW9ZXYyjK9F1dzjZ2Ks6oPW/gGXJ9TRi4W6xpg2O9kTjoy/Al+iRNsTGg+a7t1oJsi45AJ0iCXDxfvpQpjjYuqr0/83XW+HAbVjstv62DtCCBqW73UlTen6WM7TnIB1bvgzH93iLSrgUYT8e7apyCuMzfZ4HH6do0d4pkW0kXMcPiZ7WMza146GlXpwyJJrhG5L2O7ym+P9nYkKWGNbYlppDywvIm9Up/TUmd3It4RvXoUWmGCQKBgQDt7BV6gmOpeNxFQ5H7Jcg9oVGdgptpDioRyZRsxBqBvqEo+TmezVWgTtyIAS7v2YOEdPry1+yB8ykYYAzhPq/urwK9mUJN/TtmaTYmFGmrz67lJ1DK6dFE65VQ8w1Rg9Dje6xFh3NT71oaFxVCeIHiGAUFiIqY+Ok+EsZJ0HUAJwKBgQCSdLnAdthG5YjMjuPrenuQ2Pk7U5UjNiFKQWITeJvpDWTs/xFc+ZmT4sJ+4gYHwHXpDSwAC61Lgbp1+qH2RSneHx8xWrLrXIBnQvW8mtd+TFOlBBiMuXYN0VLEJ1u66UoLqBNCasv4nSNIzdfRPacnHOPM49RDgYvSwwtjWs1prQKBgBT1P+sbw0WxdM3RJvHVoS70n+jdEW2OptwMQrvWBnO5ZB7k3VOEyO8nIzwDxquwIGqxl01/AcFstwkDpP7Oc9b1qasj7fhKv0nFKNTv5WMSF1juPqqxMBL/IZcQ4fJ1RxhS05kIVntlUS2NLP0++kn9TavwHC1fd8RVCQyFhVjvAoGBAITXAzUjlwCXagHBCdKpAjNb2pSJN5j8nC1yKLdoimJVF9ovcVxXuYl+ANR9xuJ5G3oLzIEuFj2KqcohT47FJTioGe72SZtaB82h2qr798JDEQt4L5xVDFN0LhJlcLqUp+jyxqVToTcbsMl0i/Dqbxb3CVjqvbmNY3oEF+xCjFbxAoGAdGMyrF0/7zEe8j8/AiTuYImoL1VsaZyvJOBkGodOrZLHFsDirrBSwqTV15QxSd5UCbifDo9VJbCj84ksrgscoD2Bd19LStS/0TUY/WaVzN03B+JxC8QDHyrQO4e1scmm0jgTefoE8E0BfcZAtIVpJaEusBTVtdDcnZog2edQAL8="));
        
	}
}
