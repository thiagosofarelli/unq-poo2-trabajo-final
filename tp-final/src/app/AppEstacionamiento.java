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

public class AppEstacionamiento implements MovementSensor {
	
	private AsistenciaAlUsuario asistencia;
	private int numero;
	private String patente;
	private ModoApp modo;
	private Estado estado;
	private SistemaEstacionamientoMedido sem;
	
	public AppEstacionamiento(int numero, String patente, SistemaEstacionamientoMedido sem) {
		this.sem = sem;
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
		return this.getSEM().getCredito(this.numero);
	}

	public Estado getEstado() {
		return this.estado;
	}
	
	public AsistenciaAlUsuario getAsistencia() {
		return this.asistencia;
	}
	
	public SistemaEstacionamientoMedido getSEM() {
		return this.sem;
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
	public void recibirNotificacion(String notificacion) {
		System.out.println(notificacion);
	}
	
	
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
