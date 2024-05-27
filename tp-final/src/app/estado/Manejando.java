package app.estado;

import app.AppEstacionamiento;

public class Manejando extends Estado {
	
	public Manejando() {
		this.proximoEstado = new Estacionado();
	}

	@Override
	public void driving(AppEstacionamiento app){}

	@Override
	public void walking(AppEstacionamiento app){
		app.setEstado(this.getProximoEstado());
		app.recibirNotificacion("Se detectó un cambio de desplazamiento de caminando a manejando.");
		app.getModo().recibirAlertaInicioEstacionamiento(app);
	}
}
