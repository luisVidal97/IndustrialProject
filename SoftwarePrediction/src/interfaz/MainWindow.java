package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener{

	/**
	 * Constant asociated to btnLoadPrediction
	 */
	public static String BTN_LOAD= "Cargar archivo con pronósticos";
	
	
	/**
	 * Button for load the file with prediction
	 */
	private JButton btnLoadPrediction;
	
	
	/**
	 * Constructor method
	 */
	public MainWindow() {
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
