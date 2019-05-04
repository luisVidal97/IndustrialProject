package Servicios;

public class TendenciaPoryeccion implements IPronostico{

	private double[] periodos;


	private double pendiente;
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;


	public TendenciaPoryeccion(double[] values) 
	{
		periodos=values;
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
	}

	public double promedioPeriodos(double tamanio) {
		double n = 0;

		for (int i = 0; i < tamanio; i++) {
			n+=(i+1);
			
		}
		
		return n/tamanio;
	}
	
	@Override
	public double calcularPronostico() {
		
		double pendienteNumerador = 0;
		double pendienteDenominador = 0;
		for (int i = 0; i < periodos.length; i++) {

			pendienteNumerador +=(((i+1)-promedioPeriodos(periodos.length))*(periodos[i]-promedio(periodos)));
			pendienteDenominador+=(Math.pow(((i+1)-promedioPeriodos(periodos.length)), 2));

		}
		
		double pendiente = pendienteNumerador/pendienteDenominador;
		this.pendiente=pendiente;
		
		double intersection= promedio(periodos) -(pendiente*promedioPeriodos(periodos.length));
		
		System.out.println("inter:  "+ intersection);

		
		double pronostico = (pendiente*(periodos.length+1))+intersection; 
		return pronostico;
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
		 double prom = 0.0;
		    for ( int i = 0; i < v.length; i++ )
		      prom += v[i];

		    return prom / ( double ) v.length; 
	}
	
	public double getPendiente() {
		return pendiente;
	}

	public void setPendiente(double pendiente) {
		this.pendiente = pendiente;
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
