package interfaz;

import javax.swing.*;

import Servicios.ClasificacionABC;
import modelo.Articulo;

import java.awt.*;

public class ClassificationABCWindowInventary extends JFrame{

	
	private JTable tbTableData;
	
	private ClasificacionABC clasificacion;
	
	public ClassificationABCWindowInventary(ClasificacionABC clasificacionABC) {
		setTitle("Clasificación ABC");
		clasificacion=clasificacionABC;
		 //adapt Screen to resolution of computer
		 Toolkit tk = Toolkit.getDefaultToolkit();
		 Dimension d = tk.getScreenSize();
		 int width = (int)d.getWidth() / 3;
		 int height = (int)d.getHeight() / 3;
		 setSize(width,height);
		 setLocationRelativeTo(null);

		 String[] dataColumns =  {"Referencia analizada","Clase"};
		 String[][] dataRows = giveDataTable();
		 
		 tbTableData = new JTable(dataRows, dataColumns);
		 tbTableData.setEnabled(false);
		 add(new JScrollPane(tbTableData,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.CENTER);
		 //Refresh JFrame
	}

	private String[][] giveDataTable() {
		String[][] dataRows = new String[clasificacion.getRefenciasAnalizadas().size()][2];
		
		
		for (int i = 0; i < dataRows.length; i++) {
			String key = clasificacion.getReferencias().get(i);
			dataRows[i][0]=key;
			dataRows[i][1]= clasificacion.getArticulos().get(key).getClase();
		}
		

		return dataRows;
	}
	
}
