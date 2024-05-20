package sem;

import app.App;

public class PuntoDeVenta {

	private SistemaEstacionamientoMedido sem;
	private int nroControl;
	
	public PuntoDeVenta(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
		this.nroControl = 0;
	}
	
	public void registrarCargaDeCredito(App app, float monto) {
		this.sem.registrarCargaDeCredito(this, app, monto, nroControl);
		this.nroControl++;
	}

	public void registrarCompraPuntual(String patente, int cantidadHoras) {
		this.sem.registrarCompraPuntual(this, patente, cantidadHoras, nroControl);
	}
}