package estacionamiento;

import java.time.Duration;
import java.time.LocalTime;

import sem.SistemaEstacionamientoMedido;

public class EstacionamientoPorApp extends Estacionamiento {
	private int celular;
	private LocalTime horaMaxima;
	
	public EstacionamientoPorApp(String patente, LocalTime horaIn, LocalTime horaFin, int celular, LocalTime horaMax) {
		super(patente, horaIn, horaFin);
		this.celular 	= celular;
		this.horaMaxima = horaMax;
	}

	public int getCelular() {
		return this.celular;
	}
	
	@Override
	public boolean estaVigente(SistemaEstacionamientoMedido sem) {
		LocalTime horaActual = LocalTime.now();
		return this.getHoraDeFin() == null && horaActual.isBefore(this.horaMaxima);
	}
	
	@Override
	public void finalizar(SistemaEstacionamientoMedido sem) {
		LocalTime horaActual = LocalTime.now();
		this.setHoraDeFin(horaActual);
	}
}
