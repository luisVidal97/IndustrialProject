package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import modelo.Articulo;

public class AskForDataWindowPrediction extends JFrame implements ActionListener{

	
	public 	static String BTN_ACCEPT="ACEPTAR";
	
	private JLabel lbMovSimPon;
	
	private JTextField  txtMovSimPon;
		
	private JLabel lbSuavizacionSimple;
	
	private JTextField  txtSuavizacionSimple;

	private JLabel lbSuavizacionDobleAlfa;

	private JTextField  txtSuavizacionDobleAlfa;
	
	private JLabel lbSuavizacionDobleBeta;

	private JTextField  txtSuavizacionDobleBeta;
	
	private JLabel lbSuavizacionDoblePer;

	private JTextField  txtSuavizacionDoblePer;
	
	private JButton btnAccept;
	
	private PanelProductosPrediction panelProductos;
	
	private List<Articulo> listaArticulos;
	
	private MainWindow principal;
	
	private JTable tbTableData;
	
	public AskForDataWindowPrediction(List<Articulo> collection, MainWindow paginaPrincipal) {
		
		principal = paginaPrincipal;
		listaArticulos = collection;
		setTitle("Datos requeridos para los prónosticos");
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 int width = 500;
		 int height = 230;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		setLayout(null);
		
		
		lbMovSimPon = new JLabel("Número de periodos para calcular el promedio móvil simple y exponencial:");
		lbMovSimPon.setBounds(10, 10, 370, 20);
		txtMovSimPon = new JTextField();
		txtMovSimPon.setBounds(400, 10, 60, 20);
		
		lbSuavizacionSimple= new JLabel("Introduzca alfa para método de suavización exp. simple:");
		lbSuavizacionSimple.setBounds(10, 40, 370, 20);
		txtSuavizacionSimple= new JTextField();
		txtSuavizacionSimple.setBounds(400, 40, 60, 20);
		
		lbSuavizacionDobleAlfa = new JLabel("Introduzca alfa para método de suavización exp. doble:");
		lbSuavizacionDobleAlfa.setBounds(10, 70, 370, 20);
		txtSuavizacionDobleAlfa = new JTextField();
		txtSuavizacionDobleAlfa.setBounds(400, 70, 60, 20);
		
		lbSuavizacionDobleBeta = new JLabel("Introduzca Beta para método de suavización exp. doble:");
		lbSuavizacionDobleBeta.setBounds(10, 100, 370, 20);
		txtSuavizacionDobleBeta = new JTextField();
		txtSuavizacionDobleBeta.setBounds(400, 100, 60, 20);
		
		lbSuavizacionDoblePer = new JLabel("¿Con cuántos periodos anteirores desea determinar la pendiente e intersección?");
		lbSuavizacionDoblePer.setBounds(10, 130, 390, 20);
		txtSuavizacionDoblePer = new JTextField();
		txtSuavizacionDoblePer.setBounds(400, 130, 60, 20);
		
		btnAccept = new JButton(BTN_ACCEPT);
		btnAccept.addActionListener(this);
		btnAccept.setActionCommand(BTN_ACCEPT);
		btnAccept.setBounds(205, 160, 110, 20);
		
		add(lbMovSimPon);
		add(txtMovSimPon);
		add(lbSuavizacionSimple);
		add(txtSuavizacionSimple);
		add(lbSuavizacionDobleAlfa);
		add(txtSuavizacionDobleAlfa);
		add(lbSuavizacionDobleBeta);
		add(txtSuavizacionDobleBeta);
		add(lbSuavizacionDoblePer);
		add(txtSuavizacionDoblePer);
		add(btnAccept);
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(BTN_ACCEPT)) {
			try {
				int per = Integer.parseInt(txtMovSimPon.getText());
				double alfaSimple = Double.parseDouble(txtSuavizacionSimple.getText());
				double alfaDoble = Double.parseDouble(txtSuavizacionDobleAlfa.getText());
				double betaDoble= Double.parseDouble(txtSuavizacionDobleBeta.getText());
				int perSuavizacionDoble = Integer.parseInt(txtSuavizacionDoblePer.getText());
				
				double[] porcent= new double[per];
				for (int i = 0; i < porcent.length; i++) {
					String num = JOptionPane.showInputDialog(null,"Introduzca la ponderación para suavización "
							+ "exponencial  simple en el periodo de n-"+(porcent.length-i));
					double d = Double.parseDouble(num);
					porcent[i]=d;
				}
				
				if(perSuavizacionDoble>=12) {
				principal.realizarPronosticos(per, porcent, alfaSimple, alfaDoble,betaDoble,perSuavizacionDoble);
				panelProductos = new PanelProductosPrediction(listaArticulos, principal);
				}else {
					JOptionPane.showMessageDialog(null, "El periodo requerido para realizar"
							+ " suvización exponencial doble debe ser por lo menos 12. Intentelo de nuevo", "Error Periodo",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Introduzca solo números", "Formato número incorrecto",
						JOptionPane.ERROR_MESSAGE);
			}
				
		}
		
	}
	

}
