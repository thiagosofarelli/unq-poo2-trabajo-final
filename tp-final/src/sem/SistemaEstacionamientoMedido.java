package sem;

import app.App;
import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroPorCompraPuntual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaEstacionamientoMedido {
	
	private List<Zona> zonas;
	private int precioPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private List<RegistroDeCompra> registrosDeCompra;
	
	public SistemaEstacionamientoMedido(int precioPorHora, LocalTime horaInicio, LocalTime horaFin) {
		this.zonas = new ArrayList<Zona>();
		this.precioPorHora = precioPorHora;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.registrosDeCompra = new ArrayList<RegistroDeCompra>();
	}
	
	public void registrarCargaDeCredito(PuntoDeVenta puntoDeVenta, App app, float monto, int nroControl) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		RegistroDeCompra reg = new RegistroCargaDeCredito(nroControl, puntoDeVenta, fechaActual, horaActual, app, monto);
		registrosDeCompra.add(reg);
	}

	public void registrarCompraPuntual(PuntoDeVenta puntoDeVenta, String patente, int cantidadHoras, int nroControl) {
		LocalDate fechaActual = LocalDate.now();
		LocalTime horaActual = LocalTime.now();
		RegistroDeCompra reg = new RegistroPorCompraPuntual(nroControl, puntoDeVenta, fechaActual, horaActual, cantidadHoras, patente);
		registrosDeCompra.add(reg);
	}

	public void generarInfraccion(String patente, Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
