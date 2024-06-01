package app.estado;

import app.AppEstacionamiento;

public class Estacionado extends Estado {
	
	@Override
	public void driving(AppEstacionamiento app){
		app.getAsistencia().driving(app);
	}
	
	@Override
	public void registrarFinEstacionamiento(AppEstacionamiento app) {
		app.setEstado(new Manejando());
		app.getSEM().registrarEstacionamientoPorApp(app);
	}
}
