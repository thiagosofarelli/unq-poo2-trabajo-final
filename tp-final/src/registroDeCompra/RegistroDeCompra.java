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

		public int getNroControl() {
			return this.nroControl;
		}

		public PuntoDeVenta getPuntoDeVenta() {
			return this.puntoDeVenta;
		}

		public LocalDate getFecha() {
			return this.fecha;
		}

		public LocalTime getHoraRegistro() {
			return this.hora;
		}
}
