package Servicios;

public class Erratico implements IPronostico{

	private double[] periodos;
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	
	public Erratico(double[] values) 
	{
		periodos=values;
		pronostico = calcularPronostico();
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
	}
	

	@Override
	public double calcularPronostico() 
	{
		
		return periodos[periodos.length-1];
	}



	@Override
	public double calcularMAD() {
		
		double[] errores = new double[periodos.length-1];
		
		for (int i = 0; i < errores.length; i++) {
			errores[i]=(Math.abs(periodos[i+1]-periodos[i]));
			
		}
		
		
		return promedio(errores);
	}
	
	
	@Override
	public double calcularMSE() {

		double[] errores = new double[periodos.length-1];

		for (int i = 0; i < errores.length; i++) {
			errores[i]=Math.pow(Math.abs((periodos[i+1]-periodos[i])),2);
		}
		
		
		return promedio(errores);
	}


	@Override
	public double calcularMAPE() {
		double[] errores = new double[periodos.length-1];

		for (int i = 0; i < errores.length; i++) {
			errores[i]=((Math.abs(periodos[i+1]-periodos[i]))/(periodos[i+1]));
		}
		
		
		return (promedio(errores)*100);
	}	
	
	
	
	
	@Override
	public  double promedio ( double [ ] v ) {
	    double prom = 0.0;
	    for ( int i = 0; i < v.length; i++ )
	      prom += v[i];

	    return prom / ( double ) v.length;  
	  }


	public double getPronostico() {
		return pronostico;
	}


	public void setPronostico(double pronostico) {
		this.pronostico = pronostico;
	}


	public double getMad() {
		return mad;
	}


	public void setMad(double mad) {
		this.mad = mad;
	}


	public double getMse() {
		return mse;
	}


	public void setMse(double mse) {
		this.mse = mse;
	}


	public double getMape() {
		return mape;
	}


	public void setMape(double mape) {
		this.mape = mape;
	}
	
	

}
