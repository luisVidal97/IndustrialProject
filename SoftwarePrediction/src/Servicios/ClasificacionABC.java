package Servicios;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Articulo;

public class ClasificacionABC {

	private HashMap<String, Double[]> refenciasAnalizadas = new HashMap<String, Double[]>();
	private int volumenTotal;
	
	
	//Pasar de una vez el arreglo por el metodo cargar
	
	public ClasificacionABC(HashMap<String, Articulo> articulos) {

		volumenTotal=0;
		
		
		for (String key : articulos.keySet()) {	
			Articulo a = articulos.get(key);
			if (a.getVolumenAnual()!=0) {
				Double [] arregloEnteros= {a.getVolumenAnual(), 0.0 ,0.0};
				refenciasAnalizadas.put(key, arregloEnteros);
				volumenTotal+=(int)a.getVolumenAnual();
			}
		}

	}

	public void definirClase() {
		
		int i=0;
		String keyAnterior ="";
		for(String key : refenciasAnalizadas.keySet()) {
			Double [] arreglo = refenciasAnalizadas.get(key);
			arreglo[1]=(arreglo[0].doubleValue()/volumenTotal);

			if(i==1) {
				arreglo[2]=arreglo[1].doubleValue();
			}else {
				arreglo[2]= arreglo[1].doubleValue()+refenciasAnalizadas.get(keyAnterior)[2];
			}
			i++;
			keyAnterior=key;
		}

		
	}
	
}
