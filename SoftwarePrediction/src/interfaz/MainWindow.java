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
import javax.swing.JPanel;
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
	
	
	/**
	 * Button for load the file with prediction
	 */
	private JButton btnLoadPrediction;
	
	
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
		
		
		add(btnLoadPrediction, BorderLayout.SOUTH);
	
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
					crearGraficaDemanda(controlador.getArticulos());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			}
		}
		
	}
	
	
	private void crearGraficaDemanda(HashMap<String, Articulo> articulos) 
	{
		List<Articulo> valueList = new ArrayList<Articulo>(articulos.values());
		double[] periodos = new double[valueList.get(0).getDemandaArticulo().size()];
		
		for(int i = 0; i < periodos.length; i++)
		{
			periodos[i] = i + 1;
		}
		
		DefaultXYDataset dataset = new DefaultXYDataset();
		
		for(int i = 0; i < valueList.size(); i++)
		{
			String nombreArticulo = valueList.get(i).getNombreArticulo();
			ArrayList<Double> demanda = valueList.get(i).getDemandaArticulo();
			double[] valoresDemanda = new double[demanda.size()];
			
			for(int j = 0; j < demanda.size(); j++)
			{
				valoresDemanda[i] = demanda.get(i);
			}
			
			dataset.addSeries(nombreArticulo, new double[][] {periodos,valoresDemanda});
		}
      
      
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesStroke(0, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Comportamiento de la demanda", "Periodo", "Demanda", dataset);
        chart.getXYPlot().setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);   
        
        add(panel, BorderLayout.CENTER);
        
        
        btnLoadPrediction.setEnabled(false);
        this.repaint();
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
