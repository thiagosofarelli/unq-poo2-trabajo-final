package app;

import java.time.LocalTime;

import app.asistencia.Activada;
import app.asistencia.AsistenciaAlUsuario;
import app.asistencia.Desactivada;
import app.estado.Estado;
import app.modo.Automatico;
import app.modo.Manual;
import app.modo.ModoApp;
import app.sensor.MovementSensor;
import registroDeEstacionamiento.RegistroDeEstacionamientoPorApp;
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
	
	public void activarAsistencia() {
		this.asistencia = new Activada();
	}
	
	public void desactivarAsistencia() {
		this.asistencia = new Desactivada();
	}
	
	public void activarModoAutomatico() {
		this.modo = new Automatico();
	}
	
	public void activarModoManual() {
		this.modo = new Manual();
	}
	
	public ModoApp getModo() {
		return this.modo;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void recibirNotificacion(String notificacion) {
		
	}
	
	public void registrarInicioEstacionamiento() {
		this.sem.registrarEstacionamientoPorApp(numero, patente, this);
	}
	
	public void registrarFinEstacionamiento() {
		this.sem.registrarFinEstacionamientoPorApp(numero, this);
	}

	@Override
	public void driving() {
		this.estado.driving(this);
		
	}

	@Override
	public void walking() {
		this.estado.walking(this);		
	}
}
