package modelo;

import java.util.ArrayList;

public class LeadTime 
{
	private double R;
	private double tiempoTransito;
	private double nacionalizaci�nIngreso;
	private double	nivelServicio;
	private double Z;
	private ArrayList<Articulo> articulos;
	private double[] listaDemoras;
	
	public LeadTime(double r, double tiempoTransito, double nacionalizaci�nIngreso, double nivelServicio, double z,ArrayList<Articulo> articulos, double[] listaDemoras) {
		super();
		R = r;
		this.tiempoTransito = tiempoTransito;
		this.nacionalizaci�nIngreso = nacionalizaci�nIngreso;
		this.nivelServicio = nivelServicio;
		Z = z;
		this.articulos = articulos;
		this.listaDemoras = listaDemoras;
	}
	
	public double getR() {
		return R;
	}
	public void setR(double r) {
		R = r;
	}
	public double getTiempoTransito() {
		return tiempoTransito;
	}
	public void setTiempoTransito(double tiempoTransito) {
		this.tiempoTransito = tiempoTransito;
	}
	public double getNacionalizaci�nIngreso() {
		return nacionalizaci�nIngreso;
	}
	public void setNacionalizaci�nIngreso(double nacionalizaci�nIngreso) {
		this.nacionalizaci�nIngreso = nacionalizaci�nIngreso;
	}
	public double getNivelServicio() {
		return nivelServicio;
	}
	public void setNivelServicio(double nivelServicio) {
		this.nivelServicio = nivelServicio;
	}
	public double getZ() {
		return Z;
	}
	public void setZ(double z) {
		Z = z;
	}
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}
	public double[] getListaDemoras() {
		return listaDemoras;
	}
	public void setListaDemoras(double[] listaDemoras) {
		this.listaDemoras = listaDemoras;
	}
	
	
	
	

}
