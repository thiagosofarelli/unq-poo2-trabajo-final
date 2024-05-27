package observer;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroCargaDeCredito;

public interface ObserverEstacionamiento {

	public void suscribir(Entidad entidad);

	public void desuscribir(Entidad entidad);

	public void notificarInicioEstacionamiento(Estacionamiento estacionamiento);

	public void notificarFinEstacionamiento(Estacionamiento estacionamiento);

	public void notificarRecargaDeCredito(RegistroCargaDeCredito registroCargaDeCredito);
}
