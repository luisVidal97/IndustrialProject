package modelo;

import java.util.Date;

public class Orden {

	
	private String numerOrden;
	
	private Date fechaOrden;
	
	private double numeroDespacho;
	
	private double cantidadDespachada;
	
	private double cantidadAcumulada;
	
	public Orden(String numerOrden, Date fechaOrden, double numeroDespacho,double cantidadDespachada, double cantidadAcumula) {
		
		this.numerOrden = numerOrden;
		this.fechaOrden = fechaOrden;
		this.numeroDespacho = numeroDespacho;
		this.cantidadDespachada = cantidadDespachada;
		this.cantidadAcumulada = cantidadAcumula;
		
	
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


	public double getCantidadAcumulada() {
		return cantidadAcumulada;
	}


	public void setCantidadAcumulada(double cantidadAcumulada) {
		this.cantidadAcumulada = cantidadAcumulada;
	}


	public void setNumeroDespacho(double numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}


	public void setCantidadDespachada(double cantidadDespachada) {
		this.cantidadDespachada = cantidadDespachada;
	}
	
	
	
}
