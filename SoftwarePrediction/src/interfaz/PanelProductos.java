package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Articulo;

public class PanelProductos extends JFrame implements ActionListener
{
	private MainWindow ventanaPrincipal;
	public static String BTN_IZQ = "Izquierda";
	public static String BTN_DER = "Derecha";
	private JButton botonIzquierda;
	private JButton botonDerecha;
	private int posicionActual;
	private JLabel nombreArticulo;
	
	private JLabel valorPronosticoErratico;
	private JLabel valorMADErratico;
	private JLabel valorMSEErratico;
	private JLabel valorMAPEErratico;
	
	private List<Articulo> listaArticulos;
	
	public PanelProductos(List<Articulo> articulos, MainWindow principal)
	{
		ventanaPrincipal = principal;
		listaArticulos = articulos;
		posicionActual = 0;
		setLayout(new BorderLayout());
		
		botonIzquierda = new JButton("<");
		botonIzquierda.setActionCommand(BTN_IZQ);
		botonIzquierda.addActionListener(this);
		
		botonDerecha = new JButton(">");
		botonDerecha.setActionCommand(BTN_DER);
		botonDerecha.addActionListener(this);
		
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
		
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new BorderLayout());
		
		nombreArticulo = new JLabel("");
		
		panelCentro.add(BorderLayout.NORTH,nombreArticulo);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new GridLayout(25,2));
		
		JLabel relleno = new JLabel("");
		
		
		// Metodo Erraticoooo
		
		JLabel erratico = new JLabel("Metodo Erratico");
		JLabel pronosticoErratico = new JLabel("Pronostico: ");
		valorPronosticoErratico = new JLabel("");
		JLabel etiquetaVAlorMADErratico = new JLabel("Valor MAD: ");
		valorMADErratico = new JLabel("");
		JLabel etiquetaVAlorMSEErratico = new JLabel("Valor MSE: ");
		valorMSEErratico = new JLabel("");
		JLabel etiquetaVAlorMAPEErratico = new JLabel("Valor MAPE: ");
		valorMAPEErratico = new JLabel("");
		
		panelDatos.add(erratico);
		panelDatos.add(relleno);
		panelDatos.add(pronosticoErratico);
		panelDatos.add(valorPronosticoErratico);
		panelDatos.add(etiquetaVAlorMADErratico);
		panelDatos.add(valorMADErratico);
		panelDatos.add(etiquetaVAlorMSEErratico);
		panelDatos.add(valorMSEErratico);
		panelDatos.add(etiquetaVAlorMAPEErratico);
		panelDatos.add(valorMAPEErratico);
		
		
		//Metodo HorizontalPromedioMovilPonderado
		
		panelCentro.add(BorderLayout.CENTER,panelDatos);
		
		add(botonIzquierda,BorderLayout.WEST);
		add(botonDerecha,BorderLayout.EAST);
		add(panelCentro, BorderLayout.CENTER);
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
		
	}

	private void mostrarDatosArticulos(Articulo articulo) 
	{
		nombreArticulo.setText(articulo.getNombreArticulo());
		repaint();	
	}
}








