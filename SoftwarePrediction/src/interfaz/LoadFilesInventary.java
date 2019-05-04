package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadFilesInventary extends JFrame implements ActionListener{


	public static String BTN_CLASIFICACION="Clasificacion";

	public static String BTN_LEAD_TIME="LeadTime";

	private JLabel lbClasificacion;

	private JButton btnClasificacion;

	private JLabel lbLeadTime;

	private JButton btnLeadTime;


	public LoadFilesInventary() {

		setTitle("Inventario");

		//adapt Screen to resolution of computer
		int width = 400;
		int height = 150;
		setSize(width,height);
		setLocationRelativeTo(null);

		setLayout(null);


		lbClasificacion = new JLabel("Clasificación ABC:");
		lbClasificacion.setBounds(20,20, 100, 20);

		btnClasificacion = new JButton("Cargar Archivo");
		btnClasificacion.addActionListener(this);
		btnClasificacion.setActionCommand(BTN_CLASIFICACION);
		btnClasificacion.setBounds(120, 20, 200, 20);

		lbLeadTime = new JLabel("Lead Time:");
		lbLeadTime.setBounds(20, 50, 100, 20);
		
		btnLeadTime = new JButton("Cargar Archivo");
		btnLeadTime.addActionListener(this);
		btnLeadTime.setActionCommand(BTN_LEAD_TIME);
		btnLeadTime.setBounds(120, 50, 200, 20);
		
		
		add(lbClasificacion);
		add(btnClasificacion);
		add(lbLeadTime);
		add(btnLeadTime);
	}




	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals(BTN_CLASIFICACION)) {

			


		}else if(e.getActionCommand().equals(BTN_LEAD_TIME)) {


		}


	}




}
