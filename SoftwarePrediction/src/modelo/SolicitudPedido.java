package modelo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class SolicitudPedido {

	
	private double cantidadSolicitada;
	
	private Date fechaSolicitud;
	
	private String proveedor;
	
	private String numeroSolicitud;
	
	private ArrayList<Orden> ordenes = new ArrayList<Orden>();

	private double diasDemora;
	
	public SolicitudPedido(double cantidadSolicitada, Date fechaSolicitud, String proveedor, String numeroSolicitud, Orden orden) throws ParseException {
		super();
		this.cantidadSolicitada = cantidadSolicitada;
		this.fechaSolicitud = fechaSolicitud;
		this.proveedor = proveedor;
		this.numeroSolicitud = numeroSolicitud;
		diasDemora = 0;
		ordenes.add(orden);
		
		
		if(orden.getCantidadAcumulada()/cantidadSolicitada >= 0.9)
		{	
			if(diasDemora == 0)
			{
				diasDemora =  ((orden.getFechaOrden().getTime() -fechaSolicitud.getTime())/86400000);
			}
			
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

	public ArrayList<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(ArrayList<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
	
	public double getDiasDemora() {
		return diasDemora;
	}

	public void setDiasDemora(double diasDemora) {
		this.diasDemora = diasDemora;
	}

	public void agregarNuevaOrden(Orden nuevaOrden)
	{
		
			ordenes.add(nuevaOrden);
			
			if(nuevaOrden.getCantidadAcumulada()/cantidadSolicitada >= 0.9)
			{
				if(diasDemora == 0)
				{
					diasDemora =  ((nuevaOrden.getFechaOrden().getTime()-fechaSolicitud.getTime())/86400000);
				}
				
			}
		
	}

	
	
	
	
	
	
}
