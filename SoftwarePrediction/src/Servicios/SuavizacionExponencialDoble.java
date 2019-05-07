package Servicios;

public class SuavizacionExponencialDoble implements IPronostico
{

	private double[] demanda;
	private int numeroPeriodos;
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
		demanda = value;
		numeroPeriodos = numeroPer;
		alfa = alfaValor;
		beta = betaValor;
		
		st = new double[demanda.length+1];
		tt = new double[demanda.length+1];
		pronosticos = new double[demanda.length+1];
		errorABS = new double[demanda.length];
		
		calcularPendienteInterseccion();
		pronostico = calcularPronostico();
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		
	}
	
	
	
	/**
	 * it is ok
	 */
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

	/**
	 * 
	 */
	@Override
	public double calcularPronostico() {
		
		for (int i = 0; i < st.length; i++) {
			if(i<(numeroPeriodos-1)) {
				st[i]=0;
				tt[i]=0;		
			}else {
				if(i==(numeroPeriodos-1)) {
					st[i]=intersection;
					tt[i]=pendiente;
				}else {
					st[i]= (alfa*demanda[i-1])+((1-alfa)*(st[i-1]+tt[i-1]));
					System.out.println("i: "+i);
					System.out.println("alfa: "+alfa);
					System.out.println("demanda[i-1]: "+demanda[i-1]);
					System.out.println("st-1: "+st[i-1]);
					System.out.println("tt-1: "+tt[i-1]);
					tt[i]=beta*(st[i]-st[i-1])+((1-beta)*tt[i-1]);
				}
			}
			
			pronosticos[i]=Math.ceil( st[i]+tt[i] );
			if(i<= errorABS.length-1 && pronosticos[i]!=0) {
				errorABS[i]= Math.abs(demanda[i]-pronosticos[i]);
			}else if(i<= errorABS.length-1	){
				errorABS[i]=0;
			}
		System.out.println("pr: "+pronosticos[i]);
		System.out.println("st: "+st[i]);
		System.out.println("tt: "+tt[i]);
		System.out.println("----------------");
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
		
		
		return Math.ceil(count/(errorABS.length-numeroPeriodos));
	}

	@Override
	public double promedio(double[] v) {
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
