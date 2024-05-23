package registroDeEstacionamiento;

import java.time.LocalTime;

import registroDeCompra.RegistroDeCompra;

public class RegistroEstacionamientoPuntual extends RegistroDeEstacionamiento {
	private RegistroDeCompra compra;
	
	public RegistroEstacionamientoPuntual(String patente, LocalTime horaIn, LocalTime horaFin, RegistroDeCompra compra) {
		super(patente, horaIn, horaFin);
		this.compra = compra;
	}
	
	@Override
	public boolean estaVigente() {
		LocalTime horaActual = LocalTime.now();
		return horaActual.isBefore(this.getHoraDeFin());
	}

}
