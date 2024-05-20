package sem;

import app.App;
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
	
	public SistemaEstacionamientoMedido(int precioPorHora, LocalTime horaInicio, LocalTime horaFin) {
		this.zonas = new ArrayList<Zona>();
		this.precioPorHora = precioPorHora;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public void cargarCredito(PuntoDeVenta puntoDeVenta, App app, float monto, int nroRegistro) {
		// TODO Auto-generated method stub
	}

	public void registrarCompraPuntual(PuntoDeVenta puntoDeVenta, String patente, int cantidadHoras, int nroRegistro) {
		// TODO Auto-generated method stub
		
	}

	public void generarInfraccion(String patente, Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	public boolean poseeEstacionamientoVigente(String patente) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
