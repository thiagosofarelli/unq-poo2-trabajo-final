package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroPorCompraPuntual;

class GestorRegistrosDeEstacionamientoTest {

	private GestorRegistrosDeEstacionamiento gestor;
	private SistemaEstacionamientoMedido sem;
	
	@BeforeEach
	void setUp() throws Exception {
		sem = mock(SistemaEstacionamientoMedido.class);
		gestor = new GestorRegistrosDeEstacionamiento(sem);
	}

	@Test
	void ElGestorRegistraUnEstacionamientoPuntual() {
		RegistroPorCompraPuntual reg = mock(RegistroPorCompraPuntual.class);
		gestor.registrarEstacionamientoPuntual("AAA111", LocalTime.now(), 5, reg);
		assertTrue(gestor.getRegistrosDeEstacionamiento().containsKey("AAA111"));
	}

}