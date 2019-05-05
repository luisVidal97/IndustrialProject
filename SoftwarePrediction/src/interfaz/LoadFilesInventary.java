package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadFilesInventary extends JFrame implements ActionListener
{
	public static String BTN_CLASIFICACION="Clasificacion";

	public static String BTN_LEAD_TIME="LeadTime";

	private JLabel lbClasificacion;

	private JButton btnClasificacion;

	private JLabel lbLeadTime;

	private JButton btnLeadTime;
	
	private ClassificationABCWindowInventary wdClasificacion;
	
	private MainWindow principal;
	
	public LoadFilesInventary(MainWindow mainWind) {

		principal = mainWind;
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

		if(e.getActionCommand().equals(BTN_CLASIFICACION)) 
		{
			JPanel panelSeleccion = new JPanel();
			JFileChooser fc=new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XLSX", "xlsx");
			fc.setFileFilter(filtro);
			int seleccion=fc.showOpenDialog(panelSeleccion);
			 
			if(seleccion==JFileChooser.APPROVE_OPTION)
			{
			    File fichero=fc.getSelectedFile();
			    try {
			    	principal.cargarArchivoClasificacion(fichero);
//					JOptionPane.showMessageDialog(null, "¡El archivo se ha cargado correctamente!",
//							"Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
//					
					wdClasificacion = new ClassificationABCWindowInventary(principal.getControlador().getClasificacionABC());
					wdClasificacion.setVisible(true);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Archivo con formato incorrecto. Cagar de nuevo el archivo",
							"Error al cargar el archivo", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			}
		}
		else if(e.getActionCommand().equals(BTN_LEAD_TIME)) 
		{
			JPanel panelSeleccion = new JPanel();
			JFileChooser fc=new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XLSX", "xlsx");
			fc.setFileFilter(filtro);
			int seleccion=fc.showOpenDialog(panelSeleccion);
			 
			if(seleccion==JFileChooser.APPROVE_OPTION)
			{
			    File fichero=fc.getSelectedFile();
			    try {
			    	principal.cargarArchivoLeadTime(fichero);
					JOptionPane.showMessageDialog(null, "¡El archivo se ha cargado correctamente!",
							"Archivo cargado", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Archivo con formato incorrecto. Cagar de nuevo el archivo",
							"Error al cargar el archivo", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			    
			}

		}


	}




}
