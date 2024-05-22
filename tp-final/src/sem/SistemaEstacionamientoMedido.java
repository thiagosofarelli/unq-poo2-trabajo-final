package sem;

import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroPorCompraPuntual;
import registroDeInfraccion.RegistroDeInfraccion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SistemaEstacionamientoMedido {
	
	private List<Zona> zonas;
	private int precioPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private List<RegistroDeCompra> registrosDeCompra;
	private List<RegistroDeInfraccion> registrosDeInfraccion;
	private Map<Integer, Float> creditos;
	
	public SistemaEstacionamientoMedido(int precioPorHora, LocalTime horaInicio, LocalTime horaFin) {
		this.zonas 					= new ArrayList<Zona>();
		this.precioPorHora 			= precioPorHora;
		this.horaInicio 			= horaInicio;
		this.horaFin				= horaFin;
		this.registrosDeCompra 		= new ArrayList<RegistroDeCompra>();
		this.registrosDeInfraccion 	= new ArrayList<RegistroDeInfraccion>();
		this.creditos 				= new HashMap<>();
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

	public void registrarCompraPuntual(PuntoDeVenta puntoDeVenta, String patente, int cantidadHoras, int nroControl) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		RegistroDeCompra reg = new RegistroPorCompraPuntual(nroControl, puntoDeVenta, fechaActual, horaActual, cantidadHoras, patente);
		this.registrosDeCompra.add(reg);
	}

	public void generarInfraccion(String patente, Inspector inspector) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		RegistroDeInfraccion reg = new RegistroDeInfraccion(fechaActual, horaActual, inspector.getZona(), inspector, patente);
		this.registrosDeInfraccion.add(reg);
		
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
