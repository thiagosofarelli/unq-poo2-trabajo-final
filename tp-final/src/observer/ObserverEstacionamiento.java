package observer;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import suscriptor.Suscriptor;

public interface ObserverEstacionamiento {

	public void suscribir(Suscriptor entidad);

	public void desuscribir(Suscriptor entidad);

	public void notificarInicioEstacionamiento(Estacionamiento estacionamiento);

	public void notificarFinEstacionamiento(Estacionamiento estacionamiento);

	public void notificarRecargaDeCredito(RegistroDeCompra registroCargaDeCredito);
}
