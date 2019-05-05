package Servicios;

public class TendenciaPoryeccion implements IPronostico{

	private double[] demanda;
	private int numeroPeriodos;

	private double pendiente;
	private double intersection;
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	private double[] errorABS;
	private double[] pronosticos;

	public TendenciaPoryeccion(double[] values, int numPer) 
	{
		demanda=values;
		numeroPeriodos=numPer;
		pronosticos = new double[demanda.length+1];
		errorABS = new double[demanda.length];
		
		
		calcularPendienteInterseccion();
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
	}

	
	
	private void calcularPendienteInterseccion() {
		
		double pendienteNumerador = 0;
		double pendienteDenominador = 0;
		
		//periodos antes de inicializas st y tt
		double a[] = new double[numeroPeriodos];
		for (int i = 0; i < a.length; i++) {
			a[i]=demanda[i];
		
		}
		
		for (int i = 0; i < numeroPeriodos; i++) {

			pendienteNumerador +=(((i+1)-promedioPeriodos(numeroPeriodos))*(a[i]-promedio(a)));
			pendienteDenominador+=(Math.pow(((i+1)-promedioPeriodos(a.length)), 2));

		}
		
		pendiente = pendienteNumerador/pendienteDenominador;
		intersection= promedio(a) -(pendiente*promedioPeriodos(a.length));
		
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
		
		for (int i = 0; i < pronosticos.length	; i++) {
			if(i>=numeroPeriodos) {
			pronosticos[i]= ((i+1)*pendiente)+intersection;
			if(i<errorABS.length) {
			errorABS[i]= Math.abs(demanda[i]-pronosticos[i]);}
			}else {
				pronosticos[i]=0;
				errorABS[i]=0;
			}
		}
	
	
		return pronosticos[pronosticos.length-1];
	}

	@Override
	public double calcularMAD() {
		double valorMAD = 0;
		int c=0;
		for(int i = 0; i < errorABS.length;i++)
		{
			if(errorABS[i]!=0) {
				valorMAD += errorABS[i];
				c++;
			}
		}
		return Math.ceil(valorMAD/c);
	}

	@Override
	public double calcularMSE() {
		double count=0;
		double cuadrados=0;
		
		for (int i = 0; i < errorABS.length; i++) {
		
			
			if(errorABS[i]!=0) {
				cuadrados += Math.pow(errorABS[i], 2);
				count++;
			}
		}
		return Math.ceil(cuadrados/count);
	}

	@Override
	public double calcularMAPE() {
		double count =0;
		for (int i = numeroPeriodos; i < errorABS.length; i++) {
			count+=demanda[i]/errorABS[i];
		}
		
		
		return Math.ceil(count/errorABS.length-numeroPeriodos);
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
