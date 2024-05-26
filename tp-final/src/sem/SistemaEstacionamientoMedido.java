package sem;

import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroPorCompraPuntual;
import registroDeInfraccion.RegistroDeInfraccion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import app.AppEstacionamiento;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoPorApp;
import estacionamiento.EstacionamientoPuntual;

public class SistemaEstacionamientoMedido {
	
	private List<Zona> zonas;
	private int precioPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private List<RegistroDeCompra> registrosDeCompra;
	private List<RegistroDeInfraccion> registrosDeInfraccion;
	private Map<Integer, Float> creditos;
	private GestorRegistrosDeEstacionamiento gestorEstacionamientos;
	
	
	public SistemaEstacionamientoMedido(int precioPorHora, LocalTime horaInicio, LocalTime horaFin, GestorRegistrosDeEstacionamiento gestorEstacionamientos) {
		this.zonas 						= new ArrayList<Zona>();
		this.precioPorHora 				= precioPorHora;
		this.horaInicio 				= horaInicio;
		this.horaFin					= horaFin;
		this.registrosDeCompra 			= new ArrayList<RegistroDeCompra>();
		this.registrosDeInfraccion 		= new ArrayList<RegistroDeInfraccion>();
		this.gestorEstacionamientos     = gestorEstacionamientos;
		this.creditos 					= new HashMap<Integer, Float>();
	}
	
	public int getPrecioPorHora() {
		return this.precioPorHora;
	}
	
	public LocalTime getHoraFin() {
		return this.horaFin;
	}
		
	public void registrarCargaDeCredito(PuntoDeVenta puntoDeVenta, int numero, float monto, int nroControl) {
		LocalDate fechaActual 	= LocalDate.now();
		LocalTime horaActual 	= LocalTime.now();
		RegistroDeCompra reg 	= new RegistroCargaDeCredito(nroControl, puntoDeVenta, fechaActual, horaActual, numero, monto);
		this.registrosDeCompra.add(reg);
		cargarCredito(numero, monto);
	}
	
	public void cargarCredito(int numero, float monto) {
		if(creditos.containsKey(numero)) {
			creditos.replace(numero, creditos.get(numero) + monto);
		}
		else {
			creditos.put(numero, monto);
		}
	}
	
	public Float getCredito(int numero) {
		return this.creditos.get(numero);
	}
	
	public void debitarCredito(float credito, int numero) {
		float creditoActual = this.creditos.get(numero);
		this.creditos.put(numero, creditoActual - credito);
	}

	public void registrarCompraPuntual(PuntoDeVenta puntoDeVenta, String patente, int cantidadHoras, int nroControl) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual  = LocalTime.now();
		RegistroDeCompra reg  = new RegistroPorCompraPuntual(nroControl, puntoDeVenta, fechaActual, horaActual, cantidadHoras, patente);
		this.registrosDeCompra.add(reg);
		this.gestorEstacionamientos.registrarEstacionamientoPuntual(patente, horaActual, cantidadHoras, reg);
	}
	
	
	public void registrarEstacionamientoPorApp(int numero, String patente, AppEstacionamiento app ) {
		this.gestorEstacionamientos.registrarEstacionamientoPorApp(numero, patente, app);
	}
	
	public void registrarFinEstacionamientoPorApp(int numero, AppEstacionamiento app) {
		this.gestorEstacionamientos.registrarFinDeEstacionamientoPorApp(numero, app);
	}
	

	public void generarInfraccion(String patente, Inspector inspector) {
		LocalDate fechaActual 	 = LocalDate.now();
		LocalTime horaActual  	 = LocalTime.now();
		RegistroDeInfraccion reg = new RegistroDeInfraccion(fechaActual, horaActual, inspector.getZona(), inspector, patente);
		this.registrosDeInfraccion.add(reg);
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		return this.gestorEstacionamientos.poseeEstacionamientoVigente(patente);
	}
		
}
