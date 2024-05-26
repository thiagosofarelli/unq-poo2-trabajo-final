package registroDeEstacionamiento;

import java.time.LocalTime;

import registroDeCompra.RegistroDeCompra;

public class RegistroDeEstacionamientoPorApp extends RegistroDeEstacionamiento {
	private int celular;
	private LocalTime horaMaxima;
	
	public RegistroDeEstacionamientoPorApp(String patente, LocalTime horaIn, LocalTime horaFin, int celular, LocalTime horaMax) {
		super(patente, horaIn, horaFin);
		this.celular = celular;
		this.horaMaxima = horaMax;
	}
	
	@Override
	public boolean estaVigente() {
		LocalTime horaActual = LocalTime.now();
		return this.getHoraDeFin() == null && horaActual.isBefore(this.horaMaxima);
	}
}
