package sem;

import app.App;

public class PuntoDeVenta {

	private SistemaEstacionamientoMedido sem;
	private int nroRegistro;
	
	public PuntoDeVenta(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
		this.nroRegistro = 0;
	}
	
	public void cargarCredito(App app, float monto) {
		this.sem.cargarCredito(this, app, monto, nroRegistro);
		this.nroRegistro++;
	}

	public void registrarCompraPuntual(String patente, int cantidadHoras) {
		this.sem.registrarCompraPuntual(this, patente, cantidadHoras, nroRegistro);
	}
}