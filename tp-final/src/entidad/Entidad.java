package entidad;

import estacionamiento.Estacionamiento;
import observer.ObserverEstacionamiento;
import registroDeCompra.RegistroCargaDeCredito;
import sem.SistemaEstacionamientoMedido;

public interface Entidad {
	
	public void suscribirme(ObserverEstacionamiento obs);

	public void desuscribirme(ObserverEstacionamiento obs);

	public void actualizarInicioEstacionamiento(SistemaEstacionamientoMedido sem, Estacionamiento inicioDeEstacionamiento);

	public void actualizarFinEstacionamiento(SistemaEstacionamientoMedido sem, Estacionamiento finalizacionDeEstacionamiento);

	public void actualizarRecargaDeCredito(SistemaEstacionamientoMedido sem, RegistroCargaDeCredito cargaDeCredito);
	
}
