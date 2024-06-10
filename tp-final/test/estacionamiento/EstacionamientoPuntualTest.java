package estacionamiento;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import registroDeCompra.RegistroDeCompra;
import sem.SistemaEstacionamientoMedido;

public class EstacionamientoPuntualTest {
	private EstacionamientoPuntual estacionamiento;
	private SistemaEstacionamientoMedido sem;
	private RegistroDeCompra reg;
	
	@BeforeEach
	void setUp() {
		this.reg = mock(RegistroDeCompra.class);
		this.sem = mock(SistemaEstacionamientoMedido.class);
		this.estacionamiento = new EstacionamientoPuntual("ABC123", LocalTime.of(7,0), LocalTime.of(20,0), reg);
	}
	
	@Test
	void getCompraTest() {
		assertEquals(reg, estacionamiento.getCompra());
	}
	
	@Test
	void testEstacionamientoVigente() {
		when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		if (LocalTime.now().isBefore(sem.getHoraFin())) {
			assertTrue(estacionamiento.estaVigente(sem));
		} else {
			assertFalse(estacionamiento.estaVigente(sem));
		}
	}
	
	@Test
	void testEstacionamientoNoVigente() {
		when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		this.estacionamiento = new EstacionamientoPuntual("ABC123", LocalTime.of(7,0), LocalTime.of(7,0), reg);
		
		assertFalse(estacionamiento.estaVigente(sem));
	}
}
