package app;

import app.asistencia.Activada;
import app.asistencia.AsistenciaAlUsuario;
import app.asistencia.Desactivada;
import app.estado.Estado;
import app.estado.Manejando;
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
	
	public AppEstacionamiento(int numero, String patente, SistemaEstacionamientoMedido sem) {
		super(sem);
		this.asistencia = new Desactivada();
		this.numero 	= numero;
		this.patente	= patente;
		this.modo 		= new Manual();
		this.estado 	= new Manejando();
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
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public float getCredito() {
		return this.getSEM().getCredito(numero);
	}

	public Estado getEstado() {
		return this.estado;
	}
	
	public AsistenciaAlUsuario getAsistencia() {
		return this.asistencia;
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
		this.modo 		= new Automatico();
		this.asistencia = new Activada();
	}
	
	public void activarModoManual() {
		this.modo = new Manual();
	}
	
	//REGISTROS
	public void registrarInicioEstacionamiento() {
		this.estado.registrarInicioEstacionamiento(this);
	}
	
	public void registrarFinEstacionamiento() {
		this.estado.registrarFinEstacionamiento(this);
	}
	

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
