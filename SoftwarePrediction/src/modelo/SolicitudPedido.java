package modelo;

import java.util.ArrayList;
import java.util.Date;

public class SolicitudPedido {

	
	private int cantidadSolicitada;
	
	private Date fechaSolicitud;
	
	private String proveedor;
	
	private String numeroSolicitud;
	
	private ArrayList<Orden> ordenes;

	
	public SolicitudPedido(int cantidadSolicitada, Date fechaSolicitud, String proveedor, String numeroSolicitud,
			ArrayList<Orden> ordenes) {
		super();
		this.cantidadSolicitada = cantidadSolicitada;
		this.fechaSolicitud = fechaSolicitud;
		this.proveedor = proveedor;
		this.numeroSolicitud = numeroSolicitud;
		this.ordenes = ordenes;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public ArrayList<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
	
	
	
	
}
