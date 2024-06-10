package estacionamiento;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.SistemaEstacionamientoMedido;

public class EstacionamientoPorAppTest {
	private EstacionamientoPorApp estacionamiento;
	private SistemaEstacionamientoMedido sem;
	
	@BeforeEach
	void setUp() {
		this.estacionamiento = new EstacionamientoPorApp("ABC123", LocalTime.of(7, 0), null, 12345678, LocalTime.of(20, 0));
		this.sem = mock(SistemaEstacionamientoMedido.class);
	}
	
	@Test
	void getCelularTest() {
		assertEquals(12345678, estacionamiento.getCelular());
	}
	
	@Test
	void getPatenteTest() {
		assertEquals("ABC123", estacionamiento.getPatente());
	}
	
	@Test
	void estacionamientoVigente() {
		when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		assertTrue(estacionamiento.estaVigente(sem));
	}
	
	@Test
	void finalizarEstacionamiento() {
		estacionamiento.finalizar(sem);
		
		assertNotNull(estacionamiento.getHoraDeFin());
	}
	
	@Test
	void estacionamientoNoVigentePorEstarTerminado() {
		when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		estacionamiento.finalizar(sem);
		
		assertFalse(estacionamiento.estaVigente(sem));
	}
	
	@Test
	void estacionamientoNoVigentePorSerDespuesDeLaHoraMax() {
		this.estacionamiento = new EstacionamientoPorApp("ABC123", LocalTime.of(7, 0), null, 12345678, LocalTime.of(7, 0));
		when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
		assertFalse(estacionamiento.estaVigente(sem));
	}
}
