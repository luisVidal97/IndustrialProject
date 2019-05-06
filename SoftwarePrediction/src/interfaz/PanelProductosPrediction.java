package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Articulo;

public class PanelProductosPrediction extends JFrame implements ActionListener
{
	
	private MainWindow ventanaPrincipal;
	public static String BTN_IZQ = "Izquierda";
	public static String BTN_DER = "Derecha";
	public static String GRAFIC = "Grafica";
	private JButton botonIzquierda;
	private JButton botonDerecha;
	private JButton botonGrafica;
	private int posicionActual;
	private GraficaWindowPrediction ventanaGrafica;
	
	
	private JPanel panelDatos;
	private JTextField nombreArticulo;
	private JTextArea erratico;
	private JTextArea horProMovPonde;
	private JTextArea horProMovSimple;
	private JTextArea horSuaSimple;
	private JTextArea suaExpSimple;
	private JTextArea proyTende;
	
	private List<Articulo> listaArticulos;
	
	private Articulo articuloActual;
	
	public PanelProductosPrediction(List<Articulo> articulos, MainWindow principal)
	{
	
		ImageIcon ImageIcon = new ImageIcon("img/icono.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		
		 //adapt Screen to resolution of computer
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 Dimension d = tk.getScreenSize();
		 int width = (int)d.getWidth() / 2;
		 int height = (int)d.getHeight() / 2;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		
		this.setTitle("Referencias");
		ventanaPrincipal = principal;
		listaArticulos = articulos;
		posicionActual = 0;
		setLayout(new BorderLayout());
		
		botonIzquierda = new JButton("<<");
		botonIzquierda.setActionCommand(BTN_IZQ);
		botonIzquierda.addActionListener(this);
		
		botonDerecha = new JButton(">>");
		botonDerecha.setActionCommand(BTN_DER);
		botonDerecha.addActionListener(this);
		
		botonGrafica  = new JButton("Mostrar Gráfica");
		botonGrafica.setActionCommand(GRAFIC);
		botonGrafica.addActionListener(this);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		
		JComboBox<String> combo = new JComboBox<String>();
		for (int i = 0; i < listaArticulos.size(); i++)
		{
			combo.addItem(listaArticulos.get(i).getNombreArticulo());
			combo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					Articulo seleccionado = ventanaPrincipal.buscarArticulo(combo.getSelectedItem().toString());
					mostrarDatosArticulos(seleccionado);
					
				}
			});
		}
		
		panelSuperior.add(combo,BorderLayout.CENTER);
		
		JScrollPane jScrollPane = new JScrollPane();
		
		
		
		panelDatos = new JPanel();
		panelDatos.setLayout(new GridLayout(7,1));
		
		nombreArticulo = new JTextField("");
		 nombreArticulo.setHorizontalAlignment(JTextField.CENTER);
		 erratico = new JTextArea();
		 horProMovPonde = new JTextArea();
		 horProMovSimple = new JTextArea();
		 horSuaSimple = new JTextArea();
		 suaExpSimple = new JTextArea();
		 proyTende = new JTextArea();
		
		 nombreArticulo.setEditable(false);
		erratico.setEditable(false);
		horProMovPonde.setEditable(false);
		horProMovSimple.setEditable(false);
		horSuaSimple.setEditable(false);
		suaExpSimple.setEditable(false);
		proyTende.setEditable(false);
		
		panelDatos.add(erratico);
		panelDatos.add(horProMovPonde);
		panelDatos.add(horProMovSimple);
		panelDatos.add(horSuaSimple);
		panelDatos.add(suaExpSimple);
		panelDatos.add(proyTende);
		panelDatos.add(botonGrafica);
		
		jScrollPane.setViewportView(panelDatos);
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		
		aux.add(nombreArticulo, BorderLayout.NORTH);
		aux.add(jScrollPane, BorderLayout.CENTER);
		aux.add(botonIzquierda,BorderLayout.WEST);
		aux.add(botonDerecha,BorderLayout.EAST);
		add(aux, BorderLayout.CENTER);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new BorderLayout());
		
				
	//	add(botonIzquierda,BorderLayout.WEST);
	//	add(botonDerecha,BorderLayout.EAST);
		add(panelSuperior,BorderLayout.NORTH );
		setVisible(true);
		mostrarDatosArticulos(listaArticulos.get(0));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals(BTN_DER))
		{
			if(posicionActual == listaArticulos.size()-1)
			{
				posicionActual = 0;
				mostrarDatosArticulos(listaArticulos.get(posicionActual));
			}
			else
			{
				posicionActual++;
				mostrarDatosArticulos(listaArticulos.get(posicionActual));
			}
		}
		else if(e.getActionCommand().equals(BTN_IZQ))
		{
			if(posicionActual == 0)
			{
				posicionActual = listaArticulos.size()-1;
				mostrarDatosArticulos(listaArticulos.get(posicionActual));
			}
			else
			{
				posicionActual--;
				mostrarDatosArticulos(listaArticulos.get(posicionActual));
			}
		}
		else if(e.getActionCommand().equals(GRAFIC))
		{
			ventanaGrafica = new GraficaWindowPrediction(articuloActual);
			ventanaGrafica.setVisible(true);
		}
		
	}

	private void mostrarDatosArticulos(Articulo articulo) 
	{
		articuloActual = articulo;
		nombreArticulo.setText(articulo.getNombreArticulo());
		erratico.setText("");
		horProMovPonde.setText("");
		horProMovSimple.setText("");
		horSuaSimple.setText("");
		suaExpSimple.setText("");
		proyTende.setText("");
		
		nombreArticulo.setFont( nombreArticulo.getFont().deriveFont( 20f )); 
		erratico.setForeground(Color.BLACK);
		horProMovPonde.setForeground(Color.BLACK);
		horProMovSimple.setForeground(Color.BLACK);
		horSuaSimple.setForeground(Color.BLACK);
		suaExpSimple.setForeground(Color.BLACK);
		proyTende.setForeground(Color.BLACK);
		double valorMenor = 0;
		int indicadorMe = 0;
		
		if(articulo.getServicios() != null && articulo.getServicios().getErratico() != null)
		{
				erratico.setText("           Método Errático" + "\n" + 
						"Valor del pronóstico: " + articulo.getServicios().getErratico().getPronostico()  +"\n" +
						"Valor MAD: " + articulo.getServicios().getErratico().getMad()+"\n" +
						"Valor MSE: " + articulo.getServicios().getErratico().getMse() + "\n" + 
						"Valor MAPE: " + articulo.getServicios().getErratico().getMape()+ "\n");
		
				valorMenor = articulo.getServicios().getErratico().getMse();
			indicadorMe = 1;
		
			horProMovPonde.setText("           Método Horizontal promedio móvil ponderado" + "\n" + 
							"Valor del pronóstico: " +  articulo.getServicios().getHorProMovilPonde().getPronostico() + "\n" +
							"Valor MAD: " +  articulo.getServicios().getHorProMovilPonde().getMad() + "\n" +
							"Valor MSE: " + articulo.getServicios().getHorProMovilPonde().getMse() + "\n" + 
							"Valor MAPE: "+ articulo.getServicios().getHorProMovilPonde().getMape()+ "\n");
			
			if(articulo.getServicios().getHorProMovilPonde().getMse() < valorMenor)
			{
				valorMenor = articulo.getServicios().getHorProMovilPonde().getMse();
				indicadorMe = 2;
			}
			
			horProMovSimple.setText("            Método Horizontal promedio móvil simple" + "\n" + 
							"Valor del pronóstico: " +  articulo.getServicios().getHorProMovilSimple().getPronostico() + "\n" +
							"Valor MAD: " + articulo.getServicios().getHorProMovilSimple().getMad() +"\n" +
							"Valor MSE: " + articulo.getServicios().getHorProMovilSimple().getMse() +"\n" + 
							"Valor MAPE: " + articulo.getServicios().getHorProMovilSimple().getMape() + "\n");
			
			if( articulo.getServicios().getHorProMovilSimple().getMse()< valorMenor)
			{
				valorMenor =  articulo.getServicios().getHorProMovilSimple().getMse();
				indicadorMe = 3;
			}
		
			horSuaSimple.setText("           Método suavizado simple" + "\n" + 
							"Valor del pronóstico: " +  articulo.getServicios().getHorSuaziSimple().getPronostico() + "\n" +
							"Valor MAD: " + articulo.getServicios().getHorSuaziSimple().getMad() + "\n" +
							"Valor MSE: " + articulo.getServicios().getHorSuaziSimple().getMse()+ "\n" + 
							"Valor MAPE: "+ articulo.getServicios().getHorSuaziSimple().getMape() +  "\n");
			
			if(articulo.getServicios().getHorSuaziSimple().getMse() < valorMenor)
			{
				valorMenor =articulo.getServicios().getHorSuaziSimple().getMse();
				indicadorMe = 4;
				
			}
		
			suaExpSimple.setText("           Método suavizado exponencial doble" + "\n" + 
							"Valor del pronóstico: " + articulo.getServicios().getSuaviExpoDoble().getPronostico() +  "\n" +
							"Valor MAD: " + articulo.getServicios().getSuaviExpoDoble().getMad()+ "\n" +
							"Valor MSE: " + articulo.getServicios().getSuaviExpoDoble().getMse()+ "\n" + 
							"Valor MAPE: "+ articulo.getServicios().getSuaviExpoDoble().getMape() +  "\n");
			
			if(articulo.getServicios().getSuaviExpoDoble().getMse() < valorMenor)
			{
				valorMenor = articulo.getServicios().getSuaviExpoDoble().getMse();
				indicadorMe = 5;
			}
			
			proyTende.setText("           Método proyección de tendencia" + "\n" + 
							"Valor del pronóstico: " + articulo.getServicios().getProyeTende().getPronostico() +  "\n" +
							"Valor MAD: " + articulo.getServicios().getProyeTende().getMad()  +"\n" +
							"Valor MSE: " + articulo.getServicios().getProyeTende().getMse() + "\n" + 
							"Valor MAPE: " + articulo.getServicios().getProyeTende().getMape() +  "\n");
			
			if(articulo.getServicios().getProyeTende().getMse() < valorMenor)
			{
				valorMenor = articulo.getServicios().getProyeTende().getMse();
				indicadorMe = 6;
			}
			
			switch (indicadorMe)
			{
		        case 1:
		        	erratico.setForeground(Color.RED);
		        break;
		
		        case 2:
		        	horProMovPonde.setForeground(Color.RED);
		        break;
		        
		        case 3:
		        	horProMovSimple.setForeground(Color.RED);
			        break;
			        
		        case 4:
		        	horSuaSimple.setForeground(Color.RED);
			        break;
			        
		        case 5:
		        	suaExpSimple.setForeground(Color.RED);
			        break;
			        
		        case 6:
		        	proyTende.setForeground(Color.RED);
			        break;
		    }
			
			repaint();
		}
		else
		{
			erratico.setText("La referencia actualmente no tiene una demanda asociada");
			horProMovPonde.setVisible(false);
			horProMovSimple.setVisible(false);
			horSuaSimple.setVisible(false);
			suaExpSimple.setVisible(false);
			proyTende.setVisible(false);
			botonGrafica.setVisible(false);
			repaint();
		}
					
	}
}








