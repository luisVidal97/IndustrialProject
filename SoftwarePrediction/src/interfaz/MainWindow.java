package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


import modelo.Articulo;
import modelo.Controlador;

public class MainWindow extends JFrame implements ActionListener
{

	/**
	 * Constant asociated to btnLoadPrediction
	 */
	public static String BTN_LOADPRO= "CargarArchivosPronosticos";
	
	public static String BTN_GENERATE_PREDICTION="Generar Pronostico";
	
	public static String BTN_INVENTARY="Inventario";
	
	/**
	 * Button for load the file with prediction
	 */
	private JButton btnLoadPrediction;
	
	
	private JButton btnGeneratePrediction;
	
	private JButton btnInventary;
	
	private JTable tbTableData;
	
	private AskForDataWindowPrediction wdAskData;
	
	private LoadFilesInventary wdCargarInventario;
	
	/**
	 * Conexion con el controlador principal de la aplicacion
	 */
	private Controlador controlador;
	
	/**
	 * Constructor method
	 */
	public MainWindow() 
	{
		controlador = new Controlador();
		setTitle("Software Predcition");
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		 //adapt Screen to resolution of computer
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 Dimension d = tk.getScreenSize();
		 int width = (int)d.getWidth() / 2;
		 int height = (int)d.getHeight() / 2;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		btnLoadPrediction = new JButton(BTN_LOADPRO);
		btnLoadPrediction.addActionListener(this);
		btnLoadPrediction.setActionCommand(BTN_LOADPRO);
		
		btnGeneratePrediction = new JButton(BTN_GENERATE_PREDICTION);
		btnGeneratePrediction.addActionListener(this);
		btnGeneratePrediction.setActionCommand(BTN_GENERATE_PREDICTION);
		
		btnInventary = new JButton(BTN_INVENTARY);
		btnInventary.addActionListener(this);
		btnInventary.setActionCommand(BTN_INVENTARY);;
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(1,2));
		aux.add(btnGeneratePrediction);
		aux.add(btnInventary);
		
		add(btnLoadPrediction, BorderLayout.SOUTH);
		add(aux,BorderLayout.NORTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		//Logica encargada de la opcion para que el usuario escoja el archivo
		if(e.getActionCommand().equals(BTN_LOADPRO))
		{
			JPanel panelSeleccion = new JPanel();
			
			//Creamos el objeto JFileChooser
			JFileChooser fc=new JFileChooser();
			
			//Creamos el filtro
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XLSX", "xlsx");
			 
			//Le indicamos el filtro
			fc.setFileFilter(filtro);
			
			//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
			int seleccion=fc.showOpenDialog(panelSeleccion);
			 
			//Si el usuario da aceptar
			if(seleccion==JFileChooser.APPROVE_OPTION)
			{
			    //Seleccionamos el fichero
			    File fichero=fc.getSelectedFile();
			    try {
					controlador.cargarArchivoPronosticos(fichero);
					fillTable(controlador.getArticulos());
					JOptionPane.showMessageDialog(null, "¡El archivo se ha cargado correctamente!",
							"Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Archivo con formato incorrecto. Cagar de nuevo el archivo",
							"Error al cargar el archivo", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			    
			}
		}else if(e.getActionCommand().equals(BTN_GENERATE_PREDICTION))
		{
			List<Articulo> ada = new ArrayList<Articulo>(controlador.getArticulos().values());
			wdAskData  = new AskForDataWindowPrediction(ada, this);
			wdAskData.setVisible(true);
			
			
		}else if(e.getActionCommand().equals(BTN_INVENTARY)) {
			
			
			wdCargarInventario = new  LoadFilesInventary(this);
			wdCargarInventario.setVisible(true);
		}
		
		
	}
	
	
	
	/**
	 * Method server to show the table in the main window
	 * @param articulos
	 */
	private void fillTable(HashMap<String, Articulo> articulos) {
		
		String[] dataColumns =  new String[articulos.size()+1];
		
		dataColumns[0]="Periodos/Referencias";
		int i =1;
		Articulo a = null;
		 for (String key : articulos.keySet()) {
			 dataColumns[i]=key;
			 i++;
			 a=articulos.get(key);
		 }
		 
		 i=0;

		 String[][] dataRows = new String[a.getDemandaArticulo().size()][articulos.size()+1];
		 
		 for (int j = 0; j < dataRows.length; j++) {
			 dataRows[j][0]= "Per " + (j+1);
		}
		 
		 for (Articulo value : articulos.values()) {
			 for (int j = 0; j < value.getDemandaArticulo().size(); j++) {
				 
				 dataRows[j][i+1]= value.getDemandaArticulo().get(j)+"";
			 }
			 i++;
		 }

		 i=0;


		tbTableData = new JTable(dataRows, dataColumns);
		tbTableData.setEnabled(false);
		add(new JScrollPane(tbTableData,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);
		//Refresh JFrame
		SwingUtilities.updateComponentTreeUI(this);
		tbTableData.updateUI();		
		
	}




	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainWindow i = new MainWindow();
		i.setVisible(true);
	}




	public Articulo buscarArticulo(String nombre)
	{
		HashMap<String, Articulo> art = controlador.getArticulos();
		return art.get(nombre);
	}




	public void realizarPronosticos(int periodo, double[] porcent, double alfaSimple, double alfaDoble, double betaDoble, int perSuavizacionDoble) 
	{
		List<Articulo> ada = new ArrayList<Articulo>(controlador.getArticulos().values());
		
		for(int i = 0; i < ada.size(); i++)
		{
			ada.get(i).realizarPronosticos(periodo, porcent, alfaSimple, alfaDoble,betaDoble,perSuavizacionDoble);
		}
		
	}
	
	public void cargarArchivoClasificacion(File archivo)
	{
		try 
		{
			controlador.cargarArchivoClasificacion(archivo);
			JOptionPane.showMessageDialog(null, "¡El archivo se ha cargado correctamente!",
					"Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
			
			List<Articulo> ada = new ArrayList<Articulo>(controlador.getArticulos().values());
//			for(int i = 0; i < ada.size(); i++)
//			{
//				System.out.println(ada.get(i).getNombreArticulo() + " " + 
//				ada.get(i).getVolumenAnual());
//			}
		} 
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Archivo con formato incorrecto. Cagar de nuevo el archivo",
					"Error al cargar el archivo", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} 
	}
	
	public void cargarArchivoLeadTime(File archivo)
	{
		controlador.cargarArchivoLeadTime(archivo);
	}




	public Controlador getControlador() {
		return controlador;
	}




	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	
	
}
