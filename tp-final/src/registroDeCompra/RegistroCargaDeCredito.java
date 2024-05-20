package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.App;
import sem.PuntoDeVenta;

public class RegistroCargaDeCredito extends RegistroDeCompra {

	private App app;
	private float monto;
	
	public RegistroCargaDeCredito(int nroControl, PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora, App app, float monto){
		super(nroControl, puntoDeVenta, fecha, hora);
		this.app = app;
		this.monto = monto;
	}
	
}

