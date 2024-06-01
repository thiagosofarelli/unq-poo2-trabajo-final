package app.estado;

import app.AppEstacionamiento;

public class Manejando extends Estado {
	
	@Override
	public void walking(AppEstacionamiento app){
		app.getAsistencia().walking(app);
	}
	
	public void registrarInicioEstacionamiento(AppEstacionamiento app) {
		app.setEstado(new Estacionado());
		app.getSEM().registrarEstacionamientoPorApp(app);
	}
}
