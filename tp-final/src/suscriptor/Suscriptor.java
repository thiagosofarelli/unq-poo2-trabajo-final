package suscriptor;

import estacionamiento.Estacionamiento;
import observer.ObserverEstacionamiento;
import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import sem.SistemaEstacionamientoMedido;

public interface Suscriptor {
	
	public void suscribirme(ObserverEstacionamiento obs);

	public void desuscribirme(ObserverEstacionamiento obs);

	public void actualizarInicioEstacionamiento(SistemaEstacionamientoMedido sem, Estacionamiento inicioDeEstacionamiento);

	public void actualizarFinEstacionamiento(SistemaEstacionamientoMedido sem, Estacionamiento finalizacionDeEstacionamiento);

	public void actualizarRecargaDeCredito(SistemaEstacionamientoMedido sem, RegistroDeCompra cargaDeCredito);
	
}
