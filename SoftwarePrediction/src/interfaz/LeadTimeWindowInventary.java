package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeadTimeWindowInventary extends JFrame implements ActionListener{

	
	
	public static String BTN_IZQ = "Izquierda";
	public static String BTN_DER = "Derecha";
	
	private JButton btnIzquierda;
	private JButton btnDerecha;
	
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
	
	
	public LeadTimeWindowInventary() {
		
		//adapt Screen to resolution of computer
		 int width = 450;
		 int height = 300;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		 setTitle("Sistema de control periódico");
		 setLayout(new BorderLayout());
		 
		 JPanel aux = new JPanel();
		 aux.setLayout(null);
		 
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
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}
