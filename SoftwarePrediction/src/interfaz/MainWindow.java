package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

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
