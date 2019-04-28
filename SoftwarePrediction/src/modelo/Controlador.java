package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controlador
{
	/**
	 * Atributo que contiene todos los articulos, cuya clave es su nombre
	 */
	HashMap<String, Articulo> articulos = new HashMap<String, Articulo>();

	/**
	 * Este metodo se encarga de leer los datos que vienen de un archivo de excel y se encarga de guardar la informacion en el hash de articulos
	 * @param archivoExcel Archivo de excel que contiene los datos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void cargarArchivo(File archivoExcel) throws FileNotFoundException, IOException 
	{
		XSSFWorkbook libroExcel = new XSSFWorkbook(new FileInputStream(archivoExcel)); //crear un libro excel
		XSSFSheet hojaActual = libroExcel.getSheetAt(0); //acceder a la primera hoja
		boolean primerSalida = true;
		boolean segundaSalida = true;
		Row filaNombreProducto = hojaActual.getRow(2); //acceder a la  fila donde se encuentran los productos
		int contadorColumnas = 1; // Este contador me sirve para manejar las columnas
		
		while(primerSalida && filaNombreProducto!=null)
		{
			Cell celdaActual = filaNombreProducto.getCell(contadorColumnas); //Aqui cojo cada uno de los nombres de los articulos
			ArrayList<Double> demanda = new ArrayList<Double>();
			if(celdaActual !=null)
			{
				String nombreArticulo = celdaActual.getStringCellValue(); //Aqui guardo el nombre del articulo
				int parcialFila = 3; //Se inicializa en 3, ya que apartir de la fila 3 empiezan las demandas
				
				//Con este while empiezo a recorrer las celdas que estan abajo del nombre de cada articulo y asi obtengo la demandas
				while(segundaSalida)
				{
					Row filaActual = hojaActual.getRow(parcialFila); 
					if(filaActual!=null)
					{
						Cell celdaActualDeLaFilaActual = filaActual.getCell(contadorColumnas);
						if(celdaActualDeLaFilaActual != null )
						{
							double valorCelda = celdaActualDeLaFilaActual.getNumericCellValue();
							demanda.add(valorCelda);
							parcialFila++; //Con este contador paso a la siguiente demanda
						}
						else
						{
							segundaSalida = false;
						}
					}
					else
					{
						segundaSalida = false;
					}
				}
				Articulo articuloNuevo = new Articulo(nombreArticulo, demanda);
				articulos.put(nombreArticulo, articuloNuevo);
				contadorColumnas++; //Aumento esto para pasar al siguiente articulo
				segundaSalida=true;
			}
			else
			{
				primerSalida = false;
			}
		}
//		List<Articulo> valueList = new ArrayList<Articulo>(articulos.values());
//		
//		for(int i = 0; i < valueList.size(); i++)
//		{
//			String nombre =valueList.get(i).getNombreArticulo();
//			ArrayList sfas = valueList.get(i).getDemandaArticulo();
//		}		
	}
	
}
