package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Servicios.ClasificacionABC;
import Servicios.Erratico;
import Servicios.HorizontalProMovilPonderado;
import Servicios.HorizontalProMovilSimple;
import Servicios.HorizontalSuavizadoSimple;
import Servicios.TendenciaPoryeccion;

public class Controlador
{
	/**
	 * Atributo que contiene todos los articulos, cuya clave es su nombre
	 */
	HashMap<String, Articulo> articulos = new HashMap<String, Articulo>();
	HashMap<String, SolicitudPedido> solicitudes = new HashMap<String, SolicitudPedido>();
	
	private Erratico erratico;
	private TendenciaPoryeccion tendenciaProye;
	private HorizontalProMovilSimple horiProMedioSimple;
	private HorizontalProMovilPonderado horiProMedioPonderado;
	private HorizontalSuavizadoSimple horiProMedioSuvizado;
	private ClasificacionABC clasificacionABC; 

	/**
	 * Este metodo se encarga de leer los datos que vienen de un archivo de excel y se encarga de guardar la informacion en el hash de articulos
	 * @param archivoExcel Archivo de excel que contiene los datos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void cargarArchivoPronosticos(File archivoExcel) throws FileNotFoundException, IOException 
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
				
				if(articulos.get(nombreArticulo) != null)
				{
					articulos.get(nombreArticulo).setDemandaArticulo(demanda);
				}
				else
				{
					articulos.put(nombreArticulo, articuloNuevo);
				}
				contadorColumnas++; //Aumento esto para pasar al siguiente articulo
				segundaSalida=true;
			}
			else
			{
				primerSalida = false;
			}
		}
	}
	
	/**
	 * 
	 * @param archivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void cargarArchivoClasificacion(File archivo) throws FileNotFoundException, IOException
	{
		XSSFWorkbook libroExcel = new XSSFWorkbook(new FileInputStream(archivo)); //crear un libro excel
		XSSFSheet hojaActual = libroExcel.getSheetAt(0); //acceder a la primera hoja
		boolean primerSalida = true;
		int contadorFilas = 2; // Este contador me sirve para manejar las filas
		Row filaNombreProducto = hojaActual.getRow(contadorFilas); //acceder a la  fila donde se encuentran los productos
		
		ArrayList<String> referencias= new ArrayList<String>();
		
		while(primerSalida && filaNombreProducto!=null)
		{
			filaNombreProducto = hojaActual.getRow(contadorFilas);
			if(filaNombreProducto != null)
			{
				Cell celdaActualNombre = filaNombreProducto.getCell(0); //Aqui cojo cada uno de los nombres de los articulos
				if(celdaActualNombre !=null)
				{
					String nombreArticulo = celdaActualNombre.getStringCellValue(); //Aqui guardo el nombre del articulo
					Cell celdaActualValorAnual = filaNombreProducto.getCell(1); //Aqui cojo cada uno de los nombres de los articulos
					double valorAnual = celdaActualValorAnual.getNumericCellValue();
					
					Articulo articuloNuevo = new Articulo(nombreArticulo, null);
					referencias.add(nombreArticulo);
					
					if(articulos.get(nombreArticulo) != null)
					{
						articulos.get(nombreArticulo).setVolumenAnual(valorAnual);
					}
					else
					{
						articulos.put(nombreArticulo, articuloNuevo);
						articulos.get(nombreArticulo).setVolumenAnual(valorAnual);
						
					}
					contadorFilas++; //Aumento esto para pasar al siguiente articulo
				
				}
				else
				{
					primerSalida = false;
				}
			}
			else
			{
				primerSalida = false;
			}
			
		}
		
		iniciarClasificacionABC(referencias);
	}
	
	
	public void iniciarClasificacionABC(ArrayList<String> referencias) {
		
		
		clasificacionABC = new ClasificacionABC(articulos,referencias);

	}
	
	
	public ClasificacionABC getClasificacionABC() {
		return clasificacionABC;
	}

	public void setClasificacionABC(ClasificacionABC clasificacionABC) {
		this.clasificacionABC = clasificacionABC;
	}

	public void cargarArchivoLeadTime(File archivo) throws FileNotFoundException, IOException
	{
		XSSFWorkbook libroExcel = new XSSFWorkbook(new FileInputStream(archivo)); //crear un libro excel
		XSSFSheet hojaActual = libroExcel.getSheetAt(0); //acceder a la primera hoja
		boolean primerSalida = true;
		int contadorFilas = 3; // Este contador me sirve para manejar las filas
		Row filaNombreProducto = hojaActual.getRow(contadorFilas); //acceder a la  fila donde se encuentran los productos
		while(primerSalida && filaNombreProducto!=null)
		{
			filaNombreProducto = hojaActual.getRow(contadorFilas);
			if(filaNombreProducto != null)
			{
				Cell celdaActualNombre = filaNombreProducto.getCell(1); //Aqui cojo cada uno de los nombres de los solicitudes
				if(celdaActualNombre !=null)
				{
					String numeroSoliticitud = celdaActualNombre.getStringCellValue(); //Aqui guardo el nombre del articulo
					Cell celdaProveedor = filaNombreProducto.getCell(0); //Aqui cojo cada uno de los nombres de los proveedores
					String nombreProveedor = celdaProveedor.getStringCellValue();
					
					Cell celdaCantidadSolicitada = filaNombreProducto.getCell(2);  //CantidadSolicitada
					double cantidadSolicitada  = celdaCantidadSolicitada.getNumericCellValue();
					
					Cell celdaFechaSolitud = filaNombreProducto.getCell(3);  //Fecha de la solicitud
					Date fechaSolicitud = celdaFechaSolitud.getDateCellValue();
					
					//Datos de la orden 
					Cell celdaNumeroOrden = filaNombreProducto.getCell(4);
					String numeroOrder = celdaNumeroOrden.getStringCellValue();
					
					Cell celdaFechaOrden = filaNombreProducto.getCell(5);
					Date fechaOrden = celdaFechaOrden.getDateCellValue();
					
					Cell celdaNumeroDespacho= filaNombreProducto.getCell(6);
					double numeroDespacho = celdaNumeroDespacho.getNumericCellValue();
					
					Cell celdaCantidadDespachada = filaNombreProducto.getCell(7);
					double cantidadDespachada = celdaCantidadDespachada.getNumericCellValue();
					
					Orden ordenNueva = new Orden(numeroOrder, fechaOrden, numeroDespacho, cantidadDespachada);
					
					if(solicitudes.get(numeroSoliticitud) == null)
					{
						SolicitudPedido nuevaSolicitud = new SolicitudPedido(cantidadSolicitada, fechaSolicitud, nombreProveedor, numeroSoliticitud,ordenNueva);
						solicitudes.put(numeroSoliticitud, nuevaSolicitud);
					}
					else
					{
						solicitudes.get(numeroSoliticitud).agregarNuevaOrden(ordenNueva);
					}
					contadorFilas++; //Aumento esto para pasar al siguiente articulo
				}
				else
				{
					primerSalida = false;
				}
			}
			else
			{
				primerSalida = false;
			}
			
		}
	}

	public HashMap<String, Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(HashMap<String, Articulo> articulos) {
		this.articulos = articulos;
	}

	public Erratico getErratico() {
		return erratico;
	}

	public void setErratico(Erratico erratico) {
		this.erratico = erratico;
	}

	public TendenciaPoryeccion getTendenciaProye() {
		return tendenciaProye;
	}

	public void setTendenciaProye(TendenciaPoryeccion tendenciaProye) {
		this.tendenciaProye = tendenciaProye;
	}

	public HorizontalProMovilSimple getHoriProMedioSimple() {
		return horiProMedioSimple;
	}

	public void setHoriProMedioSimple(HorizontalProMovilSimple horiProMedioSimple) {
		this.horiProMedioSimple = horiProMedioSimple;
	}

	public HorizontalProMovilPonderado getHoriProMedioPonderado() {
		return horiProMedioPonderado;
	}

	public void setHoriProMedioPonderado(HorizontalProMovilPonderado horiProMedioPonderado) {
		this.horiProMedioPonderado = horiProMedioPonderado;
	}

	public HorizontalSuavizadoSimple getHoriProMedioSuvizado() {
		return horiProMedioSuvizado;
	}

	public void setHoriProMedioSuvizado(HorizontalSuavizadoSimple horiProMedioSuvizado) {
		this.horiProMedioSuvizado = horiProMedioSuvizado;
	}
	
}
