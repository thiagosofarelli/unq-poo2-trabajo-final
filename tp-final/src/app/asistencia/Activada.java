package app.asistencia;

import app.AppEstacionamiento;

public class Activada implements AsistenciaAlUsuario {

	@Override
	public void walking(AppEstacionamiento app) {
		app.recibirNotificacion("Se ha detectado un cambio de desplazamiento de auto a pie");
		app.getModo().recibirAlertaInicioEstacionamiento(app);
	}

	@Override
	public void driving(AppEstacionamiento app) {
		app.recibirNotificacion("Se ha detectado un cambio de desplazamiento de pie a auto");
		app.getModo().recibirAlertaFinEstacionamiento(app);
	}
}
