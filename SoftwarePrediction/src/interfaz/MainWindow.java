package interfaz;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import modelo.Articulo;
import modelo.Controlador;

public class MainWindow extends JFrame implements ActionListener{

	
	
	/**
	 * Constant asociated to btnLoadPrediction
	 */
	public static String BTN_LOAD= "CargarArchivos";
	
	public static String BTN_GENERATE_PREDICTION="Generar Pronostico";
	
	/**
	 * Button for load the file with prediction
	 */
	private JButton btnLoadPrediction;
	
	
	private JButton btnGeneratePrediction;
	
	
	private JTable tbTableData;
	
	
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
		
		btnLoadPrediction = new JButton(BTN_LOAD);
		btnLoadPrediction.addActionListener(this);
		btnLoadPrediction.setActionCommand(BTN_LOAD);
		
		btnGeneratePrediction = new JButton(BTN_GENERATE_PREDICTION);
		btnGeneratePrediction.addActionListener(this);
		btnGeneratePrediction.setActionCommand(BTN_GENERATE_PREDICTION);
		
		add(btnLoadPrediction, BorderLayout.SOUTH);
	
		
		add(btnGeneratePrediction,BorderLayout.NORTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		//Logica encargada de la opcion para que el usuario escoja el archivo
		if(e.getActionCommand().equals("CargarArchivos"))
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
					controlador.cargarArchivo(fichero);
					JOptionPane.showMessageDialog(null, "¡El archivo se ha cargado correctamente!",
							"Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
					fillTable(controlador.getArticulos());
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Archivo no encontrado",
							"Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No se pudo cargar el archivo correactamente",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			    
			}
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

}
