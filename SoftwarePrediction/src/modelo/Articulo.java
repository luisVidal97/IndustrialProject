package modelo;

import java.util.ArrayList;

import Servicios.ControladorServicios;

public class Articulo 
{
	
	public static String A="A";
	public static String B="B";
	public static String C="C";
	
	private String nombreArticulo;
	private ArrayList<Double> demandaArticulo;
	private ControladorServicios servicios;
	private double volumenAnual;
	private String clase;
	
	public Articulo(String nombre, ArrayList<Double> demanda ) 
	{   clase="";
		nombreArticulo = nombre;
		demandaArticulo = demanda;
		volumenAnual = 0;
	}
	

	public void realizarPronosticos(int periodo, double[] porcent, double alfa, double beta)
	{
		double[] demanda = new double[demandaArticulo.size()];
		for(int i = 0; i < demandaArticulo.size(); i++)
		{
			demanda[i] = demandaArticulo.get(i);
		}
		servicios = new ControladorServicios(demanda, periodo, porcent, alfa,beta);
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
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

	public ControladorServicios getServicios() {
		return servicios;
	}

	public void setServicios(ControladorServicios servicios) {
		this.servicios = servicios;
	}

	public double getVolumenAnual() {
		return volumenAnual;
	}

	public void setVolumenAnual(double volumenAnual) {
		this.volumenAnual = volumenAnual;
	}
	
	

}
