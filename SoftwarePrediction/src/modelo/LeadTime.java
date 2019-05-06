package modelo;

import java.util.ArrayList;

public class LeadTime 
{
	private double R;
	private double tiempoTransito;
	private double nacionalizaciónIngreso;
	private double	nivelServicio;
	private double Z;
	private ArrayList<Articulo> articulos;
	private double[] listaDemoras;
	
	public LeadTime(double r, double tiempoTransito, double nacionalizaciónIngreso, double nivelServicio, double z,ArrayList<Articulo> articulos, double[] listaDemoras) {
		super();
		R = r;
		this.tiempoTransito = tiempoTransito;
		this.nacionalizaciónIngreso = nacionalizaciónIngreso;
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
	public double getNacionalizaciónIngreso() {
		return nacionalizaciónIngreso;
	}
	public void setNacionalizaciónIngreso(double nacionalizaciónIngreso) {
		this.nacionalizaciónIngreso = nacionalizaciónIngreso;
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
