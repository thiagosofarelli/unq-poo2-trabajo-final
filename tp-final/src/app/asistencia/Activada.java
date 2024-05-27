package app.asistencia;

import app.AppEstacionamiento;

public class Activada implements AsistenciaAlUsuario {

	@Override
	public void walking(AppEstacionamiento app) {
		app.getEstado().walking(app);
	}

	@Override
	public void driving(AppEstacionamiento app) {
		app.getEstado().driving(app);

	}
}
