package Servicios;

public interface IPronostico {

	public double calcularPronostico();
	public double calcularMAD();
	public double calcularMSE();
	public double calcularMAPE();
	public  double promedio ( double [ ] v );
	
}
