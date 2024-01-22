package com.cifrado.descifrado;

/**
 * 
 * @author b198609
 *
 */
public class Llavero {

	private String idAcceso;
	private String accesoPrivadoCliente;
	private String accesoPublicoBackend;
	private String claveSeguridad;
	private String accesoSimetrico;
	private String codigoAutentificacionHash;
	private String fechaHoraExpiracion;
	
	public Llavero() {
		super();
	}

	public String getIdAcceso() {
		return idAcceso;
	}

	public void setIdAcceso(String idAcceso) {
		this.idAcceso = idAcceso;
	}

	public String getAccesoPrivadoCliente() {
		return accesoPrivadoCliente;
	}

	public void setAccesoPrivadoCliente(String accesoPrivadoCliente) {
		this.accesoPrivadoCliente = accesoPrivadoCliente;
	}

	public String getAccesoPublicoBackend() {
		return accesoPublicoBackend;
	}

	public void setAccesoPublicoBackend(String accesoPublicoBackend) {
		this.accesoPublicoBackend = accesoPublicoBackend;
	}

	public String getClaveSeguridad() {
		return claveSeguridad;
	}

	public void setClaveSeguridad(String claveSeguridad) {
		this.claveSeguridad = claveSeguridad;
	}

	public String getAccesoSimetrico() {
		return accesoSimetrico;
	}

	public void setAccesoSimetrico(String accesoSimetrico) {
		this.accesoSimetrico = accesoSimetrico;
	}

	public String getCodigoAutentificacionHash() {
		return codigoAutentificacionHash;
	}

	public void setCodigoAutentificacionHash(String codigoAutentificacionHash) {
		this.codigoAutentificacionHash = codigoAutentificacionHash;
	}
	
		public String getFechaHoraExpiracion() {
		return fechaHoraExpiracion;
	}

	public void setFechaHoraExpiracion(String fechaHoraExpiracion) {
		this.fechaHoraExpiracion = fechaHoraExpiracion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Llavero [idAcceso=");
		builder.append(idAcceso);
		builder.append(", accesoPrivadoCliente=");
		builder.append(accesoPrivadoCliente);
		builder.append(", accesoPublicoBackend=");
		builder.append(accesoPublicoBackend);
		builder.append(", claveSeguridad=");
		builder.append(claveSeguridad);
		builder.append(", accesoSimetrico=");
		builder.append(accesoSimetrico);
		builder.append(", codigoAutentificacionHash=");
		builder.append(codigoAutentificacionHash);
		builder.append(", fechaHoraExpiracion=");
		builder.append(fechaHoraExpiracion);
		builder.append("]");
		return builder.toString();
	}

	

}
