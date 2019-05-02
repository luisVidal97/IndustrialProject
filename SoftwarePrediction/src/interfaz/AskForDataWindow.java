package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AskForDataWindow extends JFrame implements ActionListener{

	
	public 	static String BTN_ACCEPT="ACEPTAR";
	
	private JLabel lbMovSimPon;
	
	private JTextField  txtMovSimPon;
		
	private JLabel lbSuavizacion;
	
	private JTextField  txtSuavizacion;
	
	private JButton btnAccept;
	
	public AskForDataWindow() {
		
		setTitle("Datos requeridos para los prónosticos");
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 int width = 600;
		 int height = 100;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		setLayout(new GridLayout(3,2));
		
		
		lbMovSimPon = new JLabel("Número de periodos para calcular el promedio movil:");
		txtMovSimPon = new JTextField();
		
		lbSuavizacion= new JLabel("Introduzca alfa para método de suavización exp. simple:");
		txtSuavizacion= new JTextField();
		
		btnAccept = new JButton(BTN_ACCEPT);
		btnAccept.addActionListener(this);
		btnAccept.setActionCommand(BTN_ACCEPT);
		
		add(lbMovSimPon);
		add(txtMovSimPon);
		add(lbSuavizacion);
		add(txtSuavizacion);
		add(new JLabel());
		add(btnAccept);
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(BTN_ACCEPT)) {
			try {
				int per = Integer.parseInt(txtMovSimPon.getText());
				double alfa = Double.parseDouble(txtSuavizacion.getText());
				
				double[] porcent= new double[per];
				for (int i = 0; i < porcent.length; i++) {
					String num = JOptionPane.showInputDialog(null,"Introduzca la ponderación para suavización "
							+ "exponencial en el periodo de n-"+(porcent.length-i));
					double d = Double.parseDouble(num);
					porcent[i]=d;
				}
				
				
				
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Introduzca solo números", "Formato número incorrecto",
						JOptionPane.ERROR_MESSAGE);
			}
				
		}
		
	}
	
	
	

}
