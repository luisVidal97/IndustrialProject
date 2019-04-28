package modelo;

import java.util.ArrayList;

public class Articulo 
{
	private String nombreArticulo;
	private ArrayList<Double> demandaArticulo;
	
	public Articulo(String nombre, ArrayList<Double> demanda ) 
	{
		nombreArticulo = nombre;
		demandaArticulo = demanda;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public ArrayList<Double> getDemandaArticulo() {
		return demandaArticulo;
	}

	public void setDemandaArticulo(ArrayList<Double> demandaArticulo) {
		this.demandaArticulo = demandaArticulo;
	}

}
