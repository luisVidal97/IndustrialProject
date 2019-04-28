package Servicios;

public class HorizontalSuavizadoSimple {
	private double[] periodos;
	private double[] alfa1;
	private double[] errorAbs1;
	public double alfa;
//	private double[] alfa2;
//	private double[] errorAbs2;
	
	public HorizontalSuavizadoSimple(double[] values, double valorAlfa) 
	{
		periodos=values;
		alfa1 = new double[periodos.length+1];
		errorAbs1 = new double[periodos.length];
		alfa = valorAlfa;
//		alfa2 = new double[periodos.length+1];
//		errorAbs2 = new double[periodos.length];
		
		
	}
	
	public double calcularPronostico()
	{
		double pronostico = 0;
		
		pronostico = calcularAlpha1(periodos.length, alfa);
		return pronostico;
	}
	
	public double MAD1()
	{
		double valorMAD1 = 0;
		alfa1[0] = periodos[0];
		for(int i = 1; i < errorAbs1.length; i++)
		{
			alfa1[i] = calcularAlpha1(i,alfa);
			errorAbs1[i] = Math.abs(periodos[i] - alfa1[i]);
			valorMAD1 += errorAbs1[i];
		}
		return valorMAD1/(errorAbs1.length-1);
	}
	
//	public double MAD2()
//	{
//		double valorMAD2 = 0;
//		alfa2[0] = periodos[0];
//		for(int i = 1; i < errorAbs2.length; i++)
//		{
//			alfa2[i] =  calcularAlpha2(i,0.5);
//			errorAbs2[i] = Math.abs(periodos[i] - alfa2[i]);
//			valorMAD2 += errorAbs2[i];
//		}
//		
//		return valorMAD2/(errorAbs2.length-1);
//	}
	
	public double calcularAlpha1(int posicion, double alpha)
	{
		double valorAlpha = 0;
		double redondeado = 0;
		
		
		valorAlpha = alfa1[posicion-1] + (alpha* (periodos[posicion-1] - alfa1[posicion-1]));
		
		redondeado = redondearDecimales(valorAlpha,0);
		return redondeado;
	}
	
//	public double calcularAlpha2(int posicion, double alpha)
//	{
//		double valorAlpha = 0;
//		double redondeado = 0;
//		
//		valorAlpha = alfa2[posicion-1] + (alpha * (periodos[posicion-1] - alfa2[posicion-1]));
//		
//		redondeado = redondearDecimales(valorAlpha,0);
//		return redondeado;
//	}
//	
	 public double redondearDecimales(double valorInicial, int numeroDecimales) 
	 {
	        double parteEntera, resultado;
	        resultado = valorInicial;
	        parteEntera = Math.floor(resultado);
	        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        resultado=Math.round(resultado);
	        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        return resultado;
	    }
}
