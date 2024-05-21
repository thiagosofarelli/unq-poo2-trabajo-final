package app;

import sem.Inspector;
import sem.SistemaEstacionamientoMedido;

public class AppInspector extends App {

	public AppInspector(SistemaEstacionamientoMedido sem) {
		super(sem);
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		return this.getSEM().poseeEstacionamientoVigente(patente);
	}
	
	public void generarInfraccion(String patente, Inspector inspector) {
	    if (!poseeEstacionamientoVigente(patente)) {
	        this.getSEM().generarInfraccion(patente, inspector);
	    }
	}
}
