package observer;

import entidad.Entidad;

public interface ObserverEstacionamiento {

	public void suscribir(Entidad entidad);

	public void desuscribir(Entidad entidad);

	public void notificarInicioEstacionamiento();

	public void notificarFinEstacionamiento();

	public void notificarRecargaDeCredito();
}
