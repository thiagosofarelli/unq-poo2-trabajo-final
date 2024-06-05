package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.PuntoDeVenta;

class ResgistroPorCompraPuntualTest {

	private PuntoDeVenta puntoDeVenta;
	private RegistroPorCompraPuntual registro;
	
	@BeforeEach
	void setUp() throws Exception {
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.registro = new RegistroPorCompraPuntual(13, puntoDeVenta, LocalDate.now(), LocalTime.now(), 3, "TS 1989");
	}

	@Test
	void testGetterCantidadDeHorasCompradas() {
		assertEquals(3, this.registro.getCantidadDeHorasCompradas());
	}

	@Test
	void testGetterPatente() {
		assertEquals("TS 1989", this.registro.getPatente());
	}
}
