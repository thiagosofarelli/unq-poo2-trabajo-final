package app;

import sem.Inspector;
import sem.SistemaEstacionamientoMedido;

public abstract class App {
	
	protected SistemaEstacionamientoMedido sem;
	
	public App(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
	}
	
	public SistemaEstacionamientoMedido getSEM() {
		return this.sem;
	}
}
