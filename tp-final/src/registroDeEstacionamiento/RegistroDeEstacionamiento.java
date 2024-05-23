package registroDeEstacionamiento;

import java.time.LocalTime;

public abstract class RegistroDeEstacionamiento {
	private String patente;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFin;
	
	public RegistroDeEstacionamiento(String patente, LocalTime horaIn, LocalTime horaFin) {
		this.patente = patente;
		this.horaDeInicio = horaIn;
		this.horaDeFin = horaFin;
	}
	
	public LocalTime getHoraDeFin() {
		return this.horaDeFin;
	}
		
	public abstract boolean estaVigente();
}
