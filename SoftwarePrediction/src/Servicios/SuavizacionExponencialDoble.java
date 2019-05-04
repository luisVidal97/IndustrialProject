package Servicios;

public class SuavizacionExponencialDoble implements IPronostico
{

	private double[] periodos;
	private int numeroPeriodo;
	private double alfa;
	private double beta;
	private double interseccion;
	private double pendiente;
	private double[] st;
	private double[] tt;
	private double[] pronosticos;
	private double[] errorABS;
	
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	
	
	
	public SuavizacionExponencialDoble(double[] value, int numeroPer, double alfaValor, double betaValor)
	{
		periodos = value;
		numeroPeriodo = numeroPer;
		alfa = alfaValor;
		beta = betaValor;
		st = new double[periodos.length];
		tt = new double[periodos.length];
		pronosticos = new double[periodos.length];
		errorABS = new double[periodos.length];
		
		calcularPendienteInterseccion();
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
		
	}
	
	
	

	private void calcularPendienteInterseccion() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public double calcularPronostico() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularMAD() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularMSE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularMAPE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double promedio(double[] v) {
		// TODO Auto-generated method stub
		return 0;
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
