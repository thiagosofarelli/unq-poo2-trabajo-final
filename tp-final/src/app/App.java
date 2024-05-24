package app;

import sem.SistemaEstacionamientoMedido;

public abstract class App {
	
	private SistemaEstacionamientoMedido sem;
	
	public App(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
	}
	
	public SistemaEstacionamientoMedido getSEM() {
		return this.sem;
	}
}
