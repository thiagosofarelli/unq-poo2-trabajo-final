package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import sem.PuntoDeVenta;

public abstract class RegistroDeCompra {

		private int nroControl;
		private PuntoDeVenta puntoDeVenta;
		private LocalDate fecha;
		private LocalTime hora;
		
		public RegistroDeCompra(int nroControl, PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora) {
			this.nroControl   = nroControl;
			this.puntoDeVenta = puntoDeVenta;
			this.fecha 		  = fecha;
			this.hora 		  = hora;
		}
}
