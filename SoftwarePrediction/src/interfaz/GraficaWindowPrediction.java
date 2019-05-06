package interfaz;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import modelo.Articulo;

public class GraficaWindowPrediction extends JFrame {

	public GraficaWindowPrediction(Articulo articuloActual) 
	{
		ImageIcon ImageIcon = new ImageIcon("img/icono.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		
		setTitle("Grafica para la referencia " + articuloActual.getNombreArticulo());
		setLayout(new BorderLayout());
		
		
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 Dimension d = tk.getScreenSize();
		 int width = (int)d.getWidth() / 2;
		 int height = (int)d.getHeight() / 2;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		 
		double[] demanda = new double[articuloActual.getDemandaArticulo().size()];
		double[] periodos = new double[articuloActual.getDemandaArticulo().size()];
		
		for(int i = 0; i < articuloActual.getDemandaArticulo().size(); i++)
		{
			demanda[i] = articuloActual.getDemandaArticulo().get(i);
			periodos[i] = i+1;
		}
		
		try {
			crearGrafica(periodos, demanda, articuloActual.getNombreArticulo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void crearGrafica(double[] periodos, double[] values, String nombreArti) throws IOException 
	{
			DefaultXYDataset dataset = new DefaultXYDataset();
	        dataset.addSeries("Referencia " + nombreArti, new double[][] {periodos,values});
	      
	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesPaint(0, Color.ORANGE);
	        renderer.setSeriesStroke(0, new BasicStroke(2));

	        JFreeChart chart = ChartFactory.createXYLineChart("Comportamiento de la demanda Referencia " + nombreArti, "Periodo", "Demanda", dataset);
	        chart.getXYPlot().setRenderer(renderer);

	        ChartPanel panel = new ChartPanel(chart);   
	        
	        add(BorderLayout.CENTER, panel);
	       
		
	}
}
