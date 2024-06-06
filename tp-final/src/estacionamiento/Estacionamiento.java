package estacionamiento;

import java.time.LocalTime;

import sem.SistemaEstacionamientoMedido;

public abstract class Estacionamiento {
	private String patente;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFin;
	
	public Estacionamiento(String patente, LocalTime horaIn, LocalTime horaFin) {
		this.patente 	  = patente;
		this.horaDeInicio = horaIn;
		this.horaDeFin 	  = horaFin;
	}

	public String getPatente() {
		return this.patente;
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
	
	public abstract boolean estaVigente(SistemaEstacionamientoMedido sem);
	
	public void finalizar(SistemaEstacionamientoMedido sem) {}
	
}
