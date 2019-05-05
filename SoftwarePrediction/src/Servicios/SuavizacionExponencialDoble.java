package Servicios;

public class SuavizacionExponencialDoble implements IPronostico
{

	private double[] periodos;
	private int numeroPeriodo;
	private double alfa;
	private double beta;
	
	private double intersection;
	private double pendiente;
	private double[] st;
	private double[] tt;
	private double[] pronosticos;
	private double[] errorABS;
	
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	
	
	/**
	 * Method Constructor
	 * @param value
	 * @param numeroPer
	 * @param alfaValor
	 * @param betaValor
	 */
	public SuavizacionExponencialDoble(double[] value, int numeroPer, double alfaValor, double betaValor)
	{
		periodos = value;
		numeroPeriodo = numeroPer;
		alfa = alfaValor;
		beta = betaValor;
		
		st = new double[periodos.length+1];
		tt = new double[periodos.length+1];
		pronosticos = new double[periodos.length+1];
		errorABS = new double[periodos.length];
		
		calcularPendienteInterseccion();
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
		
	}
	
	
	
	/**
	 * 
	 */
	private void calcularPendienteInterseccion() {
	
		double pendienteNumerador = 0;
		double pendienteDenominador = 0;
		for (int i = 0; i < periodos.length; i++) {

			pendienteNumerador +=(((i+1)-promedioPeriodos(periodos.length))*(periodos[i]-promedio(periodos)));
			pendienteDenominador+=(Math.pow(((i+1)-promedioPeriodos(periodos.length)), 2));

		}
		
		pendiente = pendienteNumerador/pendienteDenominador;
		intersection= promedio(periodos) -(pendiente*promedioPeriodos(periodos.length));
	}

	/**
	 * Help to calculate  average for use in tha calculation of slope and intersection
	 * @param tamanio
	 * @return
	 */
	public double promedioPeriodos(double tamanio) {
		double n = 0;
		for (int i = 0; i < tamanio; i++) {
			n+=(i+1);
			
		}
		
		return n/tamanio;
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
