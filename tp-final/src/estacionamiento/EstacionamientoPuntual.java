package estacionamiento;

import java.time.LocalTime;

import registroDeCompra.RegistroDeCompra;
import sem.SistemaEstacionamientoMedido;

public class EstacionamientoPuntual extends Estacionamiento {
	
	private RegistroDeCompra compra;
	
	public EstacionamientoPuntual(String patente, LocalTime horaIn, LocalTime horaFin, RegistroDeCompra compra) {
		super(patente, horaIn, horaFin);
		this.compra = compra;
	}

	public RegistroDeCompra getCompra() {
		return this.compra;
	}
	
	@Override
	public boolean estaVigente(SistemaEstacionamientoMedido sem) {
		LocalTime horaActual = LocalTime.now();
		return horaActual.isAfter(sem.getHoraFin()) || horaActual.isBefore(this.getHoraDeFin());
	}
}
