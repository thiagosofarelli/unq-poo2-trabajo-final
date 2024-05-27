package app.estado;

import app.AppEstacionamiento;

public class Estacionado extends Estado {
	public Estacionado() {
		this.proximoEstado = new Manejando();
	}
	
	@Override
	public void driving(AppEstacionamiento app){
		app.setEstado(this.getProximoEstado());
		app.recibirNotificacion("Se detectó un cambio de desplazamiento de caminando a manejando.");
		app.getModo().recibirAlertaFinEstacionamiento(app);
	}

	@Override
	public void walking(AppEstacionamiento app){}
}
