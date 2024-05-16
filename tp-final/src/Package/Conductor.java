package Package;

public class Conductor {

	String patente;
	SistemaEstacionamientoMedido sem;
	Celular celular;
	int dinero;

	
	ModoDeEstacionamiento modo = null;
	
	public Conductor(String patente, SistemaEstacionamientoMedido sem, Celular celular, int dinero) {
		this.patente = patente;
		this.sem = sem;
		this.celular = celular;
		this.dinero = dinero;
	}
	
	
	
	
}
