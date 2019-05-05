package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SolicitudPedido {

	
	private double cantidadSolicitada;
	
	private Date fechaSolicitud;
	
	private String proveedor;
	
	private String numeroSolicitud;
	
	HashMap<String, Orden> ordenes = new HashMap<String, Orden>();

	
	public SolicitudPedido(double cantidadSolicitada, Date fechaSolicitud, String proveedor, String numeroSolicitud, Orden orden) {
		super();
		this.cantidadSolicitada = cantidadSolicitada;
		this.fechaSolicitud = fechaSolicitud;
		this.proveedor = proveedor;
		this.numeroSolicitud = numeroSolicitud;
		
		if(ordenes.get(orden.getNumerOrden()) == null)
		{
			ordenes.put(orden.getNumerOrden(), orden);
		}
		
	}

	public double getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(double cantidadSolicitada) {
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

	public HashMap<String, Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(HashMap<String, Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
	public void agregarNuevaOrden(Orden nuevaOrden)
	{
		if(ordenes.get(nuevaOrden.getNumerOrden()) == null)
		{
			ordenes.put(nuevaOrden.getNumerOrden(), nuevaOrden);
		}
	}

	
	
	
	
	
	
}
