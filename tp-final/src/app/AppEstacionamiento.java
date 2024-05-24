package app;

import app.asistencia.Activada;
import app.asistencia.AsistenciaAlUsuario;
import app.asistencia.Desactivada;
import app.estado.Estado;
import app.modo.Automatico;
import app.modo.Manual;
import app.modo.ModoApp;
import app.sensor.MovementSensor;
import sem.SistemaEstacionamientoMedido;

public class AppEstacionamiento extends App implements MovementSensor {
	
	private AsistenciaAlUsuario asistencia;
	private int numero;
	private String patente;
	private ModoApp modo;
	private Estado estado;
	
	public AppEstacionamiento(AsistenciaAlUsuario asistencia, int numero, String patente, ModoApp modo, SistemaEstacionamientoMedido sem, Estado estado) {
		super(sem);
		this.asistencia = asistencia;
		this.numero = numero;
		this.patente = patente;
		this.modo = modo;
		this.estado = estado;
	}
	
	
	//SETTERS
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	//GETTERS
	public ModoApp getModo() {
		return this.modo;
	}
	
	
	//ASISTENCIA
	public void activarAsistencia() {
		this.asistencia = new Activada();
	}
	
	public void desactivarAsistencia() {
		this.asistencia = new Desactivada();
	}
	
	
	//MODO
	public void activarModoAutomatico() {
		this.modo = new Automatico();
	}
	
	public void activarModoManual() {
		this.modo = new Manual();
	}
	
	
	//REGISTROS
	public void registrarInicioEstacionamiento() {}
	
	public void registrarFinEstacionamiento() {}
	

	//NOTIFICACION
	public void recibirNotificacion(String notificacion) {}
	
	
	//OVERRIDE
	@Override
	public void driving() {
		this.estado.driving(this);
	}

	@Override
	public void walking() {
		this.estado.walking(this);
	}
}
