package app.asistencia;

import app.AppEstacionamiento;

public class Desactivada implements AsistenciaAlUsuario {

	@Override
	public void driving(AppEstacionamiento app){}

	@Override
	public void walking(AppEstacionamiento app){}
}
