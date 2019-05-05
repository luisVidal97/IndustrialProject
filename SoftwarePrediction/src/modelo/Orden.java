package modelo;

import java.util.Date;

public class Orden {

	
	private String numerOrden;
	
	private Date fechaOrden;
	
	private double numeroDespacho;
	
	private double cantidadDespachada;
	
	
	public Orden(String numerOrden, Date fechaOrden, double numeroDespacho,double cantidadDespachada) {
		
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


	public double getNumeroDespacho() {
		return numeroDespacho;
	}


	public void setNumeroDespacho(int numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}


	public double getCantidadDespachada() {
		return cantidadDespachada;
	}


	public void setCantidadDespachada(int cantidadDespachada) {
		this.cantidadDespachada = cantidadDespachada;
	}
	
	
	
}
