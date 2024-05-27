package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoPorApp extends Estacionamiento {
	private int celular;
	private LocalTime horaMaxima;
	
	public EstacionamientoPorApp(String patente, LocalTime horaIn, LocalTime horaFin, int celular, LocalTime horaMax) {
		super(patente, horaIn, horaFin);
		this.celular 	= celular;
		this.horaMaxima = horaMax;
	}
	
	@Override
	public boolean estaVigente() {
		LocalTime horaActual = LocalTime.now();
		return this.getHoraDeFin() == null && horaActual.isBefore(this.horaMaxima);
	}
}
