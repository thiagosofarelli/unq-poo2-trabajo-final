package sem;

public class Inspector {

	private SistemaEstacionamientoMedido sem;
	private Zona zona;
	
	public Inspector(SistemaEstacionamientoMedido sem, Zona zona) {
		this.sem = sem;
		this.zona = zona;
	}
	
	public boolean poseeEstacionamientoVigente(String patente) {
		return this.sem.poseeEstacionamientoVigente(patente);
	}
	
	public void generarInfraccion(String patente) {
	    if (!poseeEstacionamientoVigente(patente)) {
	        sem.generarInfraccion(patente, this);
	    }
	}

}