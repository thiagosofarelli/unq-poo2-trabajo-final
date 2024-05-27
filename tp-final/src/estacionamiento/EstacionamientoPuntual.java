package estacionamiento;

import java.time.LocalTime;

import registroDeCompra.RegistroDeCompra;

public class EstacionamientoPuntual extends Estacionamiento {
	
	private RegistroDeCompra compra;
	
	public EstacionamientoPuntual(String patente, LocalTime horaIn, LocalTime horaFin, RegistroDeCompra compra) {
		super(patente, horaIn, horaFin);
		this.compra = compra;
	}
	
	@Override
	public boolean estaVigente() {
		LocalTime horaActual = LocalTime.now();
		return horaActual.isBefore(this.getHoraDeFin());
	}
}
