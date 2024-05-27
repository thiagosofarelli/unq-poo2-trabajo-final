package estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {
	private String patente;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFin;
	
	public Estacionamiento(String patente, LocalTime horaIn, LocalTime horaFin) {
		this.patente 	  = patente;
		this.horaDeInicio = horaIn;
		this.horaDeFin 	  = horaFin;
	}
	
	public LocalTime getHoraDeInicio() {
		return this.horaDeInicio;
	}
	
	public LocalTime getHoraDeFin() {
		return this.horaDeFin;
	}
	
	public void setHoraDeFin(LocalTime horaFin) {
		this.horaDeFin = horaFin;
	}
		
	public abstract boolean estaVigente();
	
}
