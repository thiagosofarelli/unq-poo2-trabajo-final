package entidad;

import sem.ObserverEstacionamiento;

public interface Entidad {
	
	public void suscribirme(ObserverEstacionamiento obs);

	public void desuscribirme(ObserverEstacionamiento obs);

	public void actualizarInicioEstacionamiento();

	public void actualizarFinEstacionamiento();

	public void actualizarRecargaDeCredito();
	
}
