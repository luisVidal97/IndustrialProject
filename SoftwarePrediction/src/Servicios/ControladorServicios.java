package Servicios;

public class ControladorServicios 
{
	private Erratico erratico;
	private HorizontalProMovilPonderado horProMovilPonde;
	private HorizontalProMovilSimple horProMovilSimple;
	private HorizontalSuavizadoSimple horSuaziSimple;
	private SuavizacionExponencialDoble suaviExpoDoble;
	private TendenciaPoryeccion proyeTende;
	
	/**
	 * Constructor Method
	 * 
	 * @param demanda
	 * @param numero
	 * @param porcent
	 * @param alfaSimple
	 * @param alfaDoble
	 * @param betaDoble
	 * @param perSuavizacionDoble
	 */
	public ControladorServicios(double[] demanda, int numero, double[] porcent,double alfaSimple, double alfaDoble, double betaDoble, int perSuavizacionDoble)
	{
		erratico = new Erratico(demanda);
		horProMovilPonde = new HorizontalProMovilPonderado(demanda, numero, porcent);
		horProMovilSimple = new HorizontalProMovilSimple(demanda, numero);
		horSuaziSimple = new HorizontalSuavizadoSimple(demanda, alfaSimple);
		suaviExpoDoble = new SuavizacionExponencialDoble(demanda, perSuavizacionDoble, alfaDoble, betaDoble);
		proyeTende = new TendenciaPoryeccion(demanda);
	}

	public Erratico getErratico() {
		return erratico;
	}

	public void setErratico(Erratico erratico) {
		this.erratico = erratico;
	}

	public HorizontalProMovilPonderado getHorProMovilPonde() {
		return horProMovilPonde;
	}

	public void setHorProMovilPonde(HorizontalProMovilPonderado horProMovilPonde) {
		this.horProMovilPonde = horProMovilPonde;
	}

	public HorizontalProMovilSimple getHorProMovilSimple() {
		return horProMovilSimple;
	}

	public void setHorProMovilSimple(HorizontalProMovilSimple horProMovilSimple) {
		this.horProMovilSimple = horProMovilSimple;
	}

	public HorizontalSuavizadoSimple getHorSuaziSimple() {
		return horSuaziSimple;
	}

	public void setHorSuaziSimple(HorizontalSuavizadoSimple horSuaziSimple) {
		this.horSuaziSimple = horSuaziSimple;
	}

	public SuavizacionExponencialDoble getSuaviExpoDoble() {
		return suaviExpoDoble;
	}

	public void setSuaviExpoDoble(SuavizacionExponencialDoble suaviExpoDoble) {
		this.suaviExpoDoble = suaviExpoDoble;
	}

	public TendenciaPoryeccion getProyeTende() {
		return proyeTende;
	}

	public void setProyeTende(TendenciaPoryeccion proyeTende) {
		this.proyeTende = proyeTende;
	}
	
	
}
