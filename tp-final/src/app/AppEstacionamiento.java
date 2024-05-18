package app;

import app.asistencia.Activada;
import app.asistencia.AsistenciaAlUsuario;
import app.asistencia.Desactivada;
import app.modo.Automatico;
import app.modo.Manual;
import app.modo.ModoApp;

public class AppEstacionamiento extends App {
	private AsistenciaAlUsuario asistencia;
	private int numero;
	private String patente;
	private ModoApp modo;
	
	public AppEstacionamiento(AsistenciaAlUsuario asistencia, int numero, String patente, ModoApp modo) {
		this.asistencia = asistencia;
		this.numero = numero;
		this.patente = patente;
		this.modo = modo;
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
	
	public void recibirNotificacion(String notificacion) {
		
	}
	
	public void registrarInicioEstacionamiento() {
		
	}
	
	public void registrarFinEstacionamiento() {
		
	}
}
