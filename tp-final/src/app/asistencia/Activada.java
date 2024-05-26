package app.asistencia;

import app.AppEstacionamiento;

public class Activada implements AsistenciaAlUsuario {

	@Override
	public void recibirAlertaInicioEstacionamiento(AppEstacionamiento app) {
		app.getModo().recibirAlertaInicioEstacionamiento(app);
	}

	@Override
	public void recibirAlertaFinEstacionamiento(AppEstacionamiento app) {
		app.getModo().recibirAlertaFinEstacionamiento(app);

	}
}
