package sem;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppEstacionamiento;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoPorApp;
import estacionamiento.EstacionamientoPuntual;
import registroDeCompra.RegistroDeCompra;

public class GestorRegistrosDeEstacionamiento {
	private Map<String, Estacionamiento> registrosDeEstacionamientoDelDia= new HashMap<String, Estacionamiento>();
	private Map<Integer, String> registroDePatentePorCelular = new HashMap<Integer, String>();
	private List<Estacionamiento> registroEstacionamientosHistoricos = new ArrayList<Estacionamiento>();
	private SistemaEstacionamientoMedido sem;
	
	public GestorRegistrosDeEstacionamiento(SistemaEstacionamientoMedido sem) {
		this.sem = sem;
	}
	
	public void registrarEstacionamientoPuntual(String patente, LocalTime horaActual, int cantidadHoras, RegistroDeCompra reg) {
		LocalTime horaFin = horaActual.plusHours(cantidadHoras);
		EstacionamientoPuntual registro = new EstacionamientoPuntual(patente, horaActual, horaFin, reg);
		this.registrosDeEstacionamientoDelDia.put(patente, registro);
		this.sem.notificarInicioEstacionamiento(registro);
	}

	public void registrarEstacionamientoPorApp( AppEstacionamiento app ) throws Exception {
		int numero = app.getNumero();
		String patente = app.getPatente();
		if (this.registroDePatentePorCelular.containsKey(numero)) {
			throw new Exception("El numero ya tiene un estacionamiento vigente");
		} else {
			LocalTime horaActual = LocalTime.now();
			Float credito = this.sem.getCredito(numero);
			if (credito != null && credito > 0) {
				int cantHorasMax = (int) (credito / this.sem.getPrecioPorHora());
				LocalTime horaMaxPorCredito = horaActual.plusHours(cantHorasMax);
				LocalTime horaMax = horaMaxPorCredito.isBefore(this.sem.getHoraFin()) ? horaMaxPorCredito : this.sem.getHoraFin();

				Estacionamiento registro = new EstacionamientoPorApp(patente, horaActual, null, numero, horaMax);
				this.registrosDeEstacionamientoDelDia.put(patente, registro);
				this.registroDePatentePorCelular.put(numero, patente);
				app.recibirNotificacion("Se ha registrado un inicio de estacionamiento a las " + horaActual.toString() + ". La hora máxima  de fin de su estacionamiento es "
						+ horaMax.toString());
				this.sem.notificarInicioEstacionamiento(registro);
			} else {
				app.recibirNotificacion("No tiene saldo suficiente para la compra");
			}
		}
	}
	
	public void registrarFinDeEstacionamientoPorApp(AppEstacionamiento app) {
		try {
		int numero = app.getNumero();
		String patente = this.registroDePatentePorCelular.get(numero);
		if (patente != null) {
			LocalTime horaActual = LocalTime.now();
			Estacionamiento registro = this.registrosDeEstacionamientoDelDia.get(patente);
			registro.finalizar(this.sem);
			long duracion = Duration.between(registro.getHoraDeInicio(), horaActual).toHours();
			float costo = duracion * sem.getPrecioPorHora();
				sem.debitarCredito(costo, numero);
			
			this.registroDePatentePorCelular.remove(numero);
			app.recibirNotificacion("Se ha registrado un fin de estacionamiento a las " + horaActual.toString() + " horas. El mismo fue iniciado a las " +
					registro.getHoraDeInicio() + " y tuvo una duración de " + duracion + ". El costo fue de " + costo);
			this.sem.notificarFinEstacionamiento(registro);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean poseeEstacionamientoVigente(String patente) {
		return this.registrosDeEstacionamientoDelDia.containsKey(patente) && this.registrosDeEstacionamientoDelDia.get(patente).estaVigente(sem);
	}
	
	public Map<String, Estacionamiento> getRegistrosDeEstacionamiento(){
		return this.registrosDeEstacionamientoDelDia;
	}
	
	public  Map<Integer, String> getRegistrosDePatentePorCelular() {
		return this.registroDePatentePorCelular;
	}
	
	public void finalizarEstacionamientosPorFinDeFranjaHoraria() {
		this.registrosDeEstacionamientoDelDia.values().forEach(estacionamiento -> {
			estacionamiento.finalizar(this.sem); 
			this.registroEstacionamientosHistoricos.add(estacionamiento);
		}); 
		this.registrosDeEstacionamientoDelDia.clear();
	}

	public Map<String, Estacionamiento> getRegistrosDeEstacionamientoDelDia() {
		return this.registrosDeEstacionamientoDelDia;
	}
	
}
