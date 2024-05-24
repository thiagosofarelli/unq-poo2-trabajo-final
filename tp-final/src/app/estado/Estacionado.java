package app.estado;

import app.AppEstacionamiento;

public class Estacionado extends Estado {
	
	@Override
	public void driving(AppEstacionamiento app) {
		app.setEstado(this.getProximoEstado());
		app.getModo().recibirAlertaFinEstacionamiento(app);
	}

	@Override
	public void walking(AppEstacionamiento app) {

	}

}
