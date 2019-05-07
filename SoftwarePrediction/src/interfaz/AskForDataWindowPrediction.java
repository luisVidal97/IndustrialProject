package interfaz;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import modelo.Articulo;

public class AskForDataWindowPrediction extends JFrame implements ActionListener{

	
	public 	static String BTN_ACCEPT="ACEPTAR";
	
	private JLabel lbMovSim;
	
	private JTextField  txtMovSim;

	private JLabel lbMovPon;

	private JTextField  txtMovPon;
		
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
	
	
	public AskForDataWindowPrediction(List<Articulo> collection, MainWindow paginaPrincipal) {
		
		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/img/icono.png"));
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		
		principal = paginaPrincipal;
		listaArticulos = collection;
		setTitle("Datos requeridos para los prónosticos");
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 int width = 500;
		 int height = 270;
		 setSize(width,height);
		 setLocationRelativeTo(null);
		
		setLayout(null);
		
		
		lbMovSim = new JLabel("Número de periodos para calcular el promedio móvil simple:");
		lbMovSim.setBounds(30, 10, 370, 20);
		txtMovSim = new JTextField();
		txtMovSim.setBounds(400, 10, 60, 20);
		
		lbMovPon = new JLabel("Número de periodos para calcular el promedio móvil ponderado:");
		lbMovPon.setBounds(30, 40, 370, 20);
		txtMovPon = new  JTextField();
		txtMovPon.setBounds(400, 40, 60, 20);
		
		lbSuavizacionSimple= new JLabel("Introduzca alfa para método de suavización exp. simple:");
		lbSuavizacionSimple.setBounds(30, 70, 370, 20);
		txtSuavizacionSimple= new JTextField();
		txtSuavizacionSimple.setBounds(400, 70, 60, 20);
		
		lbSuavizacionDobleAlfa = new JLabel("Introduzca alfa para método de suavización exp. doble:");
		lbSuavizacionDobleAlfa.setBounds(30, 100, 370, 20);
		txtSuavizacionDobleAlfa = new JTextField();
		txtSuavizacionDobleAlfa.setBounds(400, 100, 60, 20);
		
		lbSuavizacionDobleBeta = new JLabel("Introduzca Beta para método de suavización exp. doble:");
		lbSuavizacionDobleBeta.setBounds(30, 130, 370, 20);
		txtSuavizacionDobleBeta = new JTextField();
		txtSuavizacionDobleBeta.setBounds(400, 130, 60, 20);
		
		lbSuavizacionDoblePer = new JLabel("Periodos utilizados en proyección de tend. y suavización expo. doble:");
		lbSuavizacionDoblePer.setBounds(30, 160, 390, 20);
		txtSuavizacionDoblePer = new JTextField();
		txtSuavizacionDoblePer.setBounds(400, 160, 60, 20);
		
		btnAccept = new JButton(BTN_ACCEPT);
		btnAccept.addActionListener(this);
		btnAccept.setActionCommand(BTN_ACCEPT);
		btnAccept.setBounds(205, 200, 110, 20);
		
		add(lbMovSim);
		add(txtMovSim);
		add(lbMovPon);
		add(txtMovPon);
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
				int perSimp = Integer.parseInt(txtMovSim.getText());
				int perPon = Integer.parseInt(txtMovPon.getText());
				double alfaSimple = Double.parseDouble(txtSuavizacionSimple.getText());
				double alfaDoble = Double.parseDouble(txtSuavizacionDobleAlfa.getText());
				double betaDoble= Double.parseDouble(txtSuavizacionDobleBeta.getText());
				int perSuavizacionDoble = Integer.parseInt(txtSuavizacionDoblePer.getText());
				
				boolean datosLlenados = true;
				double[] porcent= new double[perPon];
				for (int i = 0; i < porcent.length; i++) {
					String num = JOptionPane.showInputDialog(null,"Introduzca la ponderación para promedio "
							+ "móvil ponderado en el periodo de n-"+(porcent.length-i));
					if(num != null)
					{
						double d = Double.parseDouble(num);
						porcent[i]=d;
					}
					else
					{
						datosLlenados = false;
					}
					
				}
				
				if(perSuavizacionDoble>=12) 
				{
					if(datosLlenados)
					{
						principal.realizarPronosticos(perSimp, perPon, porcent, alfaSimple, alfaDoble,betaDoble,perSuavizacionDoble);
						panelProductos = new PanelProductosPrediction(listaArticulos, principal);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No se ingresaron correctamente las ponderaciones para cada uno de los periodos requeridos", "Error Ponderación",JOptionPane.ERROR_MESSAGE);
					}
				
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
