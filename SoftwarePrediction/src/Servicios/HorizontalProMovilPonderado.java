package Servicios;

public class HorizontalProMovilPonderado implements IPronostico{

	private double[] periodos;
	private int numeroPeriodo;
	private double[] porcentajes;
	
	private static double[] pronosticoParcial;
	private static double[] errorAbsoluto;
	private static double[] errorAbsolutoCuadrado;
	private static double[] errorRelativo;
	
	public HorizontalProMovilPonderado(double[] values, int numero, double[] porcent) 
	{
		periodos=values;
		numeroPeriodo = numero;
		porcentajes= porcent;
		
		pronosticoParcial = new double[periodos.length];
		errorAbsoluto = new double[periodos.length];
		errorAbsolutoCuadrado = new double[periodos.length];
		errorRelativo = new double[periodos.length];
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
		
		double p = pronostico;
		double entero = (int)p;
		if(p-entero==0) {
			return p;
		}else {
			return p+1;
		}
		
		
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
		return (valorMAPE/(periodos.length-numeroPeriodo))*100;
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

}