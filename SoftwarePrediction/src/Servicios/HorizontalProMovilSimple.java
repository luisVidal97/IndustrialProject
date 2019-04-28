package Servicios;

public class HorizontalProMovilSimple implements IPronostico{

	private double[] periodos;
	private int numeroPeriodo;

	private static double[] pronosticoParcial;
	private static double[] errorAbsoluto;
	private static double[] errorAbsolutoCuadrado;
	private static double[] errorRelativo;
	
	public HorizontalProMovilSimple(double[] values, int numero) 
	{
		periodos=values;
		numeroPeriodo = numero;
		
		pronosticoParcial = new double[periodos.length];
		errorAbsoluto = new double[periodos.length];
		errorAbsolutoCuadrado = new double[periodos.length];
		errorRelativo = new double[periodos.length];
	}
	
	@Override
	public double calcularPronostico() 
	{
		double pronostico = 0;
		for(int i = periodos.length-(numeroPeriodo); i < periodos.length;i++)
		{
			pronostico += periodos[i];
		}
		
		double p = (pronostico/numeroPeriodo);
		double entero = (int)p;
		if(p-entero==0) {
			return p;
		}else {
			return p+1;
		}
		
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
		for(int i = (int) (d-numeroPeriodo); i < d;i++)
		{
			parcial += periodos[i];
		}
		return parcial/numeroPeriodo;
	}

}
