package app.estado;

import app.AppEstacionamiento;

public class Manejando extends Estado {

	@Override
	public void driving(AppEstacionamiento app) {

	}

	@Override
	public void walking(AppEstacionamiento app) {
		app.setEstado(this.getProximoEstado());
		app.getModo().recibirAlertaInicioEstacionamiento(app);
	}

}
