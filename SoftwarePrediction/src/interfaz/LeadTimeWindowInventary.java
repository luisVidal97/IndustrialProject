package interfaz;

import javax.swing.*;

import modelo.Articulo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LeadTimeWindowInventary extends JFrame implements ActionListener{

	
	
	public static String BTN_IZQ = "Izquierda";
	public static String BTN_DER = "Derecha";
	
	private JButton btnIzquierda;
	private JButton btnDerecha;
	
	private JLabel lbNombreArticulo;
	private JTextField txtNombreArticulo;
	
	private JLabel lbLeadTime;
	private JTextField txtLeadTime;
	
	private JLabel lbDesviacion;
	private JTextField txtDesviacion;
	
	private JLabel lbCantidadPedir;
	private JTextField txtCantidadPedir;

	private JLabel lbInventarioMax;
	private JTextField txtInventarioMax;
	
	private JLabel lbInventarioSeguridad;
	private JTextField txtInventarioSeguridad;
	
	private ArrayList<Articulo> listaArticulos;
	
	private int posicionActual;
	
	private LoadFilesInventary principal;
	
	public LeadTimeWindowInventary(LoadFilesInventary prin, ArrayList<Articulo> articulos) 
	{
		
		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/img/icono.png"));
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		
		listaArticulos = articulos;
		principal = prin;
		posicionActual = 0;
		//adapt Screen to resolution of computer
		 int width = 450;
		 int height = 300;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		 setTitle("Sistema de control periódico");
		 setLayout(new BorderLayout());
		 
		 JPanel aux = new JPanel();
		 aux.setLayout(new GridLayout(6,2));
		 
		 
		 lbNombreArticulo = new JLabel("Referencia: ");
		 lbNombreArticulo.setBounds(10,30,200,20);
		  
		 txtNombreArticulo = new JTextField();
		 txtNombreArticulo.setBounds(240, 30, 60, 20);
		 txtNombreArticulo.setEditable(false);
		 
		 lbLeadTime = new JLabel("Lead Time: ");
		 lbLeadTime.setBounds(10,30,200,20);
		 
		 txtLeadTime = new JTextField();
		 txtLeadTime.setBounds(240, 30, 60, 20);
		 txtLeadTime.setEditable(false);
		 
		 lbDesviacion = new JLabel("Desviación estándar Lead Time:");
		 lbDesviacion.setBounds(10, 70, 200, 20);
		 
		 txtDesviacion = new JTextField();
		 txtDesviacion.setBounds(240, 70, 60, 20);
		 txtDesviacion.setEditable(false);
		 
		 lbCantidadPedir = new JLabel("Cantidad a pedir:");
		 lbCantidadPedir.setBounds(10, 110, 200, 20);
		 
		 txtCantidadPedir = new JTextField();
		 txtCantidadPedir.setBounds(240, 110, 60, 20);
		 txtCantidadPedir.setEditable(false);
		 
		 lbInventarioMax = new JLabel("Inventario Máximo:");
		 lbInventarioMax.setBounds(10, 150, 200, 20);
		 
		 txtInventarioMax = new JTextField();
		 txtInventarioMax.setBounds(240, 150, 60, 20);
		 txtInventarioMax.setEditable(false);
		 
		 lbInventarioSeguridad = new JLabel("Inventario de seguridad:");
		 lbInventarioSeguridad.setBounds(10, 190, 200, 20);
		 
		 txtInventarioSeguridad = new JTextField();
		 txtInventarioSeguridad.setBounds(240, 190, 60, 20);
		 txtInventarioSeguridad.setEditable(false);
		 
		 aux.add(lbNombreArticulo);
		 aux.add(txtNombreArticulo);
		 aux.add(lbLeadTime);
		 aux.add(txtLeadTime);
		 aux.add(lbDesviacion);
		 aux.add(txtDesviacion);
		 aux.add(lbCantidadPedir);
		 aux.add(txtCantidadPedir);
		 aux.add(lbInventarioMax);
		 aux.add(txtInventarioMax);
		 aux.add(lbInventarioSeguridad);
		 aux.add(txtInventarioSeguridad);
		 
		 btnIzquierda = new JButton("<<");
		 btnIzquierda.addActionListener(this);
		 btnIzquierda.setActionCommand(BTN_IZQ);
		 
		 
		 btnDerecha = new JButton(">>");
		 btnDerecha.addActionListener(this);
		 btnDerecha.setActionCommand(BTN_DER);
		 
		 
		 add(aux, BorderLayout.CENTER);
		 add(btnIzquierda, BorderLayout.WEST);
		 add(btnDerecha, BorderLayout.EAST);
		 
		 JComboBox<String> combo = new JComboBox<String>();
			for (int i = 0; i < listaArticulos.size(); i++)
			{
				combo.addItem(listaArticulos.get(i).getNombreArticulo());
				combo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Articulo seleccionado = principal.buscarArticulo(combo.getSelectedItem().toString());
						mostrarDatosArticulos(seleccionado);
						
					}
				});
			}
		 
			add(combo, BorderLayout.NORTH);

		 mostrarDatosArticulos(listaArticulos.get(0));
	}


	private void mostrarDatosArticulos(Articulo articulo)
	{
		txtNombreArticulo.setText(articulo.getNombreArticulo());
		txtLeadTime.setText(articulo.getLeadTime() + "");
		txtDesviacion.setText(articulo.getDesviacionLeadTime() + "");
		txtCantidadPedir.setText(articulo.getCantidadPedir() + "");
		txtInventarioMax.setText(articulo.getInventarioMaximo() + "");
		txtInventarioSeguridad.setText(articulo.getInventarioSeguridad() + "");
		repaint();	
		
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
	
}
