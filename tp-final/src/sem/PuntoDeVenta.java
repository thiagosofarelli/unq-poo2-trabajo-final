package sem;

import java.time.LocalTime;

import app.App;
import registroDeEstacionamiento.RegistroEstacionamientoPuntual;

public class PuntoDeVenta {

	private SistemaEstacionamientoMedido sem;
	private int nroControl;
	
	public PuntoDeVenta(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
		this.nroControl = 0;
	}
	
	public void registrarCargaDeCredito(int numero, float monto) {
		this.sem.registrarCargaDeCredito(this, numero, monto, nroControl);
		this.nroControl++;
	}

	public void registrarCompraPuntual(String patente, int cantidadHoras) {
		this.sem.registrarCompraPuntual(this, patente, cantidadHoras, nroControl);
		this.nroControl++;
	}

	public int getNumeroDeControlActual() {
		return this.nroControl;
	}
}