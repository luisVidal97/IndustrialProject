package Servicios;

public class HorizontalProMovilPonderado implements IPronostico{

	private double[] periodos;
	private int numeroPeriodo;
	private double[] porcentajes;
	private static double[] pronosticoParcial;
	private static double[] errorAbsoluto;
	private static double[] errorAbsolutoCuadrado;
	private static double[] errorRelativo;
	
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	
	
	public HorizontalProMovilPonderado(double[] values, int numero, double[] porcent) 
	{
		periodos=values;
		numeroPeriodo = numero;
		porcentajes= porcent;
		
		
		
		pronosticoParcial = new double[periodos.length];
		errorAbsoluto = new double[periodos.length];
		errorAbsolutoCuadrado = new double[periodos.length];
		errorRelativo = new double[periodos.length];
		
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
	}
	
	@Override
	public double calcularPronostico() 
	{
		double pronostico = 0;
		int contador = 0;
		for(int i = periodos.length-(numeroPeriodo); i < periodos.length;i++)
		{
			pronostico += periodos[i] * porcentajes[contador];
			contador++;
		}
		
		double p = Math.ceil(pronostico);
		return p;
		
		
//		int a = (int)pronostico;
//		return a+1;
	}

	@Override
	public double calcularMAD() 
	{
		double valorMAD = 0;
		
		for(int i = numeroPeriodo; i < periodos.length;i++)
		{
			pronosticoParcial[i] = pronosticosParcial(i);
			double errorAbso = Math.abs(periodos[i] - pronosticoParcial[i]);
			errorAbsoluto[i] = errorAbso;
			valorMAD += errorAbso;
		}
		return valorMAD/(periodos.length-numeroPeriodo);
	}

	@Override
	public double calcularMSE() 
	{
		double valorMSE = 0;
		
		for(int i = numeroPeriodo; i < periodos.length;i++)
		{
			double errorAbso= errorAbsoluto[i];
			valorMSE += errorAbso * errorAbso;
			errorAbsolutoCuadrado[i] = errorAbso*errorAbso;
		}
		return valorMSE/(periodos.length-numeroPeriodo);
	}

	@Override
	public double calcularMAPE() 
	{
		double valorMAPE = 0;
		
		for(int i = numeroPeriodo; i < periodos.length;i++)
		{
			double errorRela= errorAbsoluto[i]/periodos[i];
			valorMAPE += errorRela;
			errorRelativo[i] = errorRela;
		}
		return (valorMAPE/(periodos.length-numeroPeriodo));
	}

	@Override
	public double promedio(double[] v) {
		 double prom = 0.0;
		    for ( int i = 0; i < v.length; i++ )
		      prom += v[i];

		    return prom / ( double ) v.length; 
	}
	
	private double pronosticosParcial(int d)
	{
		double parcial = 0;
		int contador = 0;
		for(int i = (int) (d-numeroPeriodo); i < d;i++)
		{
			parcial += periodos[i] * porcentajes[contador];
			contador++;
		}
		return parcial;
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