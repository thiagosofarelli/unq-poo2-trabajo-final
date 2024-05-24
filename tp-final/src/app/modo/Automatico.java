package app.modo;

import app.AppEstacionamiento;

public class Automatico implements ModoApp {

	@Override
	public void recibirAlertaInicioEstacionamiento(AppEstacionamiento app) {
		app.registrarInicioEstacionamiento();
		app.recibirNotificacion("Se ha realizado un inicio de estacionamiento de forma automática");
	}

	@Override
	public void recibirAlertaFinEstacionamiento(AppEstacionamiento app) {
		app.registrarFinEstacionamiento();
		app.recibirNotificacion("Se ha realizado un fin de estacionamiento de forma automática");
	}
}
