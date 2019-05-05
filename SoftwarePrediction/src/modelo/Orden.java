package modelo;

import java.util.Date;

public class Orden {

	
	private String numerOrden;
	
	private Date fechaOrden;
	
	private int numeroDespacho;
	
	private int cantidadDespachada;
	
	
	public Orden(String numerOrden, Date fechaOrden, int numeroDespacho,int cantidadDespachada) {
		
		this.numerOrden = numerOrden;
		this.fechaOrden = fechaOrden;
		this.numeroDespacho = numeroDespacho;
		this.cantidadDespachada = cantidadDespachada;
		
	
	}


	public String getNumerOrden() {
		return numerOrden;
	}


	public void setNumerOrden(String numerOrden) {
		this.numerOrden = numerOrden;
	}


	public Date getFechaOrden() {
		return fechaOrden;
	}


	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}


	public int getNumeroDespacho() {
		return numeroDespacho;
	}


	public void setNumeroDespacho(int numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}


	public int getCantidadDespachada() {
		return cantidadDespachada;
	}


	public void setCantidadDespachada(int cantidadDespachada) {
		this.cantidadDespachada = cantidadDespachada;
	}
	
	
	
}
