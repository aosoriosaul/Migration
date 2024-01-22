package com.cifrado.descifrado;

/**
 * Exception para la generacion de la cadena cifrada
 * 
 * @author B248426
 *
 */
public class CifradoDescifradoException extends RuntimeException {
	private static final long serialVersionUID = -1635958220641183543L;

	private String mensajeError;

	public CifradoDescifradoException(String mensajeError) {
		this.setMensajeError(mensajeError);
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
