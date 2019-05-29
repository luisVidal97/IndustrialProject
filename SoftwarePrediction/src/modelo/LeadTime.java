package modelo;

import java.util.ArrayList;

public class LeadTime 
{
	
	private double leadTime;
	private double desviacionEstandar;
	
	private double R;
	private double tiempoTransito;
	private double nacionalizaciónIngreso;
	private double	nivelServicio;
	private double Z;
	private ArrayList<Articulo> articulos;
	private double[] listaDemoras;
	
	////////////////////////////////////////////
	
	private int numeroDemoras;
	private double valorMaximo;
	private double valorMinimo;
	private double valorRango;
	private double numeroIntervalos;
	private double ancho;
	
	
	////////////////////////////////////////
	
	private double[] limiteInferior;
	private double[] limiteSuperior;
	private double[] marcaClase;
	private double[] numeroOcurrencias;
	private double[] probaOcurre;
	private double[] cuadrados;
	
	/**
	 * 
	 * @param r
	 * @param tiempoTransito
	 * @param nacionalizaciónIngreso
	 * @param nivelServicio
	 * @param z
	 * @param articulos
	 * @param listaDemoras
	 */
	public LeadTime(double r, double tiempoTransito, double nacionalizaciónIngreso, double nivelServicio, double z,ArrayList<Articulo> articulos, double[] listaDemoras) {
		super();
		R = r;
		this.tiempoTransito = tiempoTransito;
		this.nacionalizaciónIngreso = nacionalizaciónIngreso;
		this.nivelServicio = nivelServicio;
		Z = z;
		this.articulos = articulos;
		this.listaDemoras = listaDemoras;
		
		numeroDemoras = listaDemoras.length;
		valorMaximo = listaDemoras[0];
		valorMinimo = listaDemoras[0];
		valorRango = 0;
		numeroIntervalos = 0;
		ancho = 0;
		
		llenarPrimerCuadro();
		
		calcularLeadDesvia();
		
	}
	
	private void calcularLeadDesvia() {
		
		double l = 0;
		
		for (int i = 0; i < marcaClase.length; i++) {
			l+= marcaClase[i]*probaOcurre[i];
		}
		
		//leadtimeParcial
		leadTime = Math.ceil(l)/30;
		
//		System.out.println(" este fue el limet tiem inicial local : " +leadTime);
		
		double v =0;
		for (int i = 0; i < probaOcurre.length; i++) {
			v+=probaOcurre[i]*cuadrados[i];
		}
		
		
		double leadTimeAuxiliar = leadTime*30;
		
		double varianza=(v-leadTimeAuxiliar*leadTimeAuxiliar);
		
		double desviacionEstandarAuxiliar= Math.sqrt(varianza);
		desviacionEstandar=desviacionEstandarAuxiliar/30;
		
		//leadtime Global
		leadTime = leadTime+tiempoTransito/30+nacionalizaciónIngreso/30;
		
		for (int i = 0; i < articulos.size(); i++) {
			articulos.get(i).setLeadTime(leadTime);
			articulos.get(i).setDesviacionLeadTime(desviacionEstandar);
			articulos.get(i).calcularCantidadPedir(R, Z);
		}
		
	}

	/**
	 * 
	 */
	private void llenarPrimerCuadro() 
	{
		for(int i = 0; i < listaDemoras.length; i++)
		{
			if(valorMinimo>listaDemoras[i])
			{
				valorMinimo=listaDemoras[i];
			}
			if(valorMaximo<listaDemoras[i])
			{
				valorMaximo=listaDemoras[i];
			}
		}
		
		valorRango = valorMaximo - valorMinimo;
		numeroIntervalos = Math.ceil(1+(3.3*(Math.log(numeroDemoras) / Math.log(10))));
		ancho = Math.ceil(valorRango/numeroIntervalos);
		
//		System.out.println("tamaño: " + listaDemoras.length);
//		System.out.println("min: " + valorMinimo);
//		System.out.println("max: " + valorMaximo);
//		System.out.println("rango: " + valorRango);
//		System.out.println("intervalor: " + numeroIntervalos);
//		System.out.println("ancho: " + ancho);
		
		llenarSegundoCuadro();
		
	}

	/**
	 * 
	 */
	private void llenarSegundoCuadro() 
	{
		limiteInferior = new double[(int) numeroIntervalos];
		limiteSuperior = new double[(int) numeroIntervalos];
		marcaClase = new double[(int) numeroIntervalos];
		numeroOcurrencias = new double[(int) numeroIntervalos];
		probaOcurre = new double[(int) numeroIntervalos];
		cuadrados = new double[(int) numeroIntervalos];
		
		for(int i = 0; i < limiteInferior.length; i++)
		{
			if(i==0)
			{
				limiteInferior[i] = valorMinimo;
				limiteSuperior[i] = limiteInferior[i] + ancho;
				marcaClase[i] = (limiteInferior[i]  + limiteSuperior[i] )/2;
				numeroOcurrencias[i] = calcularOcurrencias(limiteInferior[i] , limiteSuperior[i] , 0);
				probaOcurre[i] = numeroOcurrencias[i] / numeroDemoras;
				cuadrados[i] = marcaClase[i] * marcaClase[i];
			}
			else
			{
				limiteInferior[i] = limiteSuperior[i-1];
				limiteSuperior[i] = limiteInferior[i] + ancho;
				marcaClase[i] = (limiteInferior[i]  + limiteSuperior[i] )/2;
				numeroOcurrencias[i] = calcularOcurrencias(limiteInferior[i] , limiteSuperior[i], 1 );
				probaOcurre[i] = numeroOcurrencias[i] / numeroDemoras;
				cuadrados[i] = marcaClase[i] * marcaClase[i];
			}
			
//			System.out.println(limiteInferior[i] + "  " +  limiteSuperior[i] + "  " + marcaClase[i] + "  " + numeroOcurrencias[i] + "  " + probaOcurre[i] + "  " +  cuadrados[i]);
		}
		
	}

	/**
	 * 
	 * @param inferior
	 * @param superior
	 * @param indic
	 * @return
	 */
	private double calcularOcurrencias(double inferior, double superior, int indic) 
	{
		double ocurren = 0;
		
		for(int i = 0; i < listaDemoras.length; i++)
		{
			
			if(indic == 0)
			{
				if(listaDemoras[i] >= inferior && listaDemoras[i] <= superior)
				{
					ocurren++;
				}
			}
			else
			{
				if(listaDemoras[i] > inferior && listaDemoras[i] <= superior)
				{
					ocurren++;
				}
			}
		}
		
		return ocurren;
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
