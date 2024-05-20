package sem;

import app.App;
import registroDeCompra.RegistroDeCompra;

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
	
	public void registrarCargaDeCredito(PuntoDeVenta puntoDeVenta, App app, float monto, int nroRegistro) {
		// INSTANCIAR UN RegistroCargaDeCredito Y ANIADIRLO A this.registrosDeCompra
	}

	public void registrarCompraPuntual(PuntoDeVenta puntoDeVenta, String patente, int cantidadHoras, int nroRegistro) {
		// INSTANCIAR UN RegistroPorCompraPuntual Y ANIADIRLO A this.registrosDeCompra
		
	}

	public void generarInfraccion(String patente, Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
