package Servicios;

public class HorizontalSuavizadoSimple implements IPronostico{
	private double[] periodos;
	private double[] alfa1;
	private double[] errorAbs1;
	private double[] errorAbsolutoCuadrado;
	private double[] errorRelativo;
	public double alfa;
	
	private double pronostico;
	private double mad;
	private double mse;
	private double mape;
	
	public HorizontalSuavizadoSimple(double[] values, double valorAlfa) 
	{
		periodos=values;
		alfa1 = new double[periodos.length+1];
		errorAbs1 = new double[periodos.length];
		errorAbsolutoCuadrado = new double[periodos.length];
		errorRelativo = new double[periodos.length];
		alfa = valorAlfa;
		
		
		mad = calcularMAD();
		mse = calcularMSE();
		mape = calcularMAPE();
		pronostico = calcularPronostico();
		
	}
	
	public double calcularPronostico()
	{
		double pronostico = 0;
		
		pronostico = calcularAlpha1(periodos.length, alfa);
		
		System.out.println("alfa " + alfa1[periodos.length-1] +  " demanda " + periodos[periodos.length-1]);
		return pronostico;
	}
	
	@Override
	public double calcularMAD()
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
	
	@Override
	public double calcularMSE() 
	{
		double valorMSE = 0;
		
		for(int i = 1; i < periodos.length;i++)
		{
			double errorAbso= errorAbs1[i];
			valorMSE += errorAbso * errorAbso;
			errorAbsolutoCuadrado[i] = errorAbso*errorAbso;
		}
		return valorMSE/(periodos.length-1);
	}
	
	@Override
	public double calcularMAPE() 
	{
		double valorMAPE = 0;
		
		for(int i = 1; i < periodos.length;i++)
		{
			double errorRela= errorAbs1[i]/periodos[i];
			valorMAPE += errorRela;
			errorRelativo[i] = errorRela;
		}
		return (valorMAPE/(periodos.length-1));
	}
	
	
	public double calcularAlpha1(int posicion, double alpha)
	{
		double valorAlpha = 0;
		double redondeado = 0;
		
		
		valorAlpha = alfa1[posicion-1] + (alpha* (periodos[posicion-1] - alfa1[posicion-1]));
		
		redondeado = redondearDecimales(valorAlpha,0);
		return redondeado;
	}
	
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

	@Override
	public double promedio(double[] v) {
		// TODO Auto-generated method stub
		return 0;
	}
	 
	 
}
