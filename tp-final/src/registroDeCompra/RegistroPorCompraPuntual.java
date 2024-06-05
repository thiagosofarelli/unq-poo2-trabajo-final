package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import sem.PuntoDeVenta;

public class RegistroPorCompraPuntual extends RegistroDeCompra {

	private int cantidadDeHorasCompradas;
	private String patente;
	
	public RegistroPorCompraPuntual(int nroControl, PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora, int cantidadDeHorasCompradas, String patente){
		super(nroControl, puntoDeVenta, fecha, hora);
		this.cantidadDeHorasCompradas = cantidadDeHorasCompradas;
		this.patente 				  = patente;
	}

	public int getCantidadDeHorasCompradas() {
		return this.cantidadDeHorasCompradas;
	}

	public String getPatente() {
		return this.patente;
	}
}
