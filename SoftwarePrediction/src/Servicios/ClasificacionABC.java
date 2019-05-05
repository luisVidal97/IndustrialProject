package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import modelo.Articulo;

public class ClasificacionABC {

	private HashMap<String, Double[]> refenciasAnalizadas = new HashMap<String, Double[]>();
	HashMap<String, Articulo> articulos;
	ArrayList<String> referencias;
	private int volumenTotal;
	
	/**
	 * Constructor Method
	 * @param articulos
	 * @param refe
	 */
	public ClasificacionABC(HashMap<String, Articulo> articulos, ArrayList<String> refe) {
		
		referencias = refe;
		this.articulos = articulos;
		volumenTotal=0;
		
		for (int i = 0; i < referencias.size(); i++) {
			Articulo a = articulos.get(referencias.get(i));
			Double [] arregloEnteros= {a.getVolumenAnual(), 0.0 ,0.0};
			volumenTotal+=(int)a.getVolumenAnual();
			refenciasAnalizadas.put(referencias.get(i), arregloEnteros);
		}
		
		definirClase();
	
	}

	/**
	 * Method which it define the classes of product
	 */
	public void definirClase() {
		
		ordenarReferenciasMayoMenor();
		
		for (int j = 0; j < referencias.size(); j++) {
			Double [] arreglo = refenciasAnalizadas.get(referencias.get(j));
			arreglo[1]=(arreglo[0].doubleValue()/volumenTotal);
			
			if(j==0) {
				arreglo[2]=arreglo[1].doubleValue();
			}else {
				arreglo[2]= arreglo[1].doubleValue()+refenciasAnalizadas.get(referencias.get(j-1))[2];
			}
			
			
			if(arreglo[2].doubleValue()<0.81) {
				articulos.get(referencias.get(j)).setClase("A");
			}else if(arreglo[2].doubleValue()>=0.81 && arreglo[2].doubleValue()<0.96) {
				articulos.get(referencias.get(j)).setClase("B");
			}else {
				articulos.get(referencias.get(j)).setClase("C");
			}
			
		}

	}


	private void ordenarReferenciasMayoMenor() {
		
		
	}

	public HashMap<String, Double[]> getRefenciasAnalizadas() {
		return refenciasAnalizadas;
	}


	public void setRefenciasAnalizadas(HashMap<String, Double[]> refenciasAnalizadas) {
		this.refenciasAnalizadas = refenciasAnalizadas;
	}


	public HashMap<String, Articulo> getArticulos() {
		return articulos;
	}


	public void setArticulos(HashMap<String, Articulo> articulos) {
		this.articulos = articulos;
	}


	public int getVolumenTotal() {
		return volumenTotal;
	}


	public void setVolumenTotal(int volumenTotal) {
		this.volumenTotal = volumenTotal;
	}


	public ArrayList<String> getReferencias() {
		return referencias;
	}


	public void setReferencias(ArrayList<String> referencias) {
		this.referencias = referencias;
	}
	
	
	
}
