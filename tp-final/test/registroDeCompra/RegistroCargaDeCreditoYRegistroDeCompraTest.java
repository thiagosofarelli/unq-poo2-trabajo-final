package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.PuntoDeVenta;

class RegistroCargaDeCreditoYRegistroDeCompraTest {

	private PuntoDeVenta puntoDeVenta;
	private RegistroCargaDeCredito registro;
	
	@BeforeEach
	void setUp() throws Exception {
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.registro = new RegistroCargaDeCredito(12, puntoDeVenta, LocalDate.now(), LocalTime.now(), 1113121989, 1989);
	}

	@Test
	void testGetterNroControl() {
		assertEquals(12, this.registro.getNroControl());
	}
	
	@Test
	void testGetterPuntoDeVenta() {
		assertEquals(this.puntoDeVenta, this.registro.getPuntoDeVenta());
	}
	
	@Test
	void testGetterFecha() {
		assertEquals(LocalDate.now(), this.registro.getFecha());
	}
	
	//@Test
	//void testGetterHora() {
		//assertEquals(LocalTime.now(), this.registro.getHoraRegistro());
	//}
	
	@Test
	void testGetterNumero() {
		assertEquals(1113121989, this.registro.getNumero());
	}
	
	@Test
	void testMonto() {
		assertEquals(1989, this.registro.getMonto());
	}
}
