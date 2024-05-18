package app.asistencia;

import app.AppEstacionamiento;

public interface AsistenciaAlUsuario {
	
	public void recibirAlertaInicioEstacionamiento(AppEstacionamiento app);
	public void recibirAlertaFinEstacionamiento(AppEstacionamiento app);
}
