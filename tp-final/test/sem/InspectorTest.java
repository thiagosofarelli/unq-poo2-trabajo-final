package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InspectorTest {

	private Inspector inspector;
	private SistemaEstacionamientoMedido sem;
	private Zona zona;
	
	@BeforeEach
	void setUp() throws Exception {
		// Set up SEM and Zona dummy
		sem = mock(SistemaEstacionamientoMedido.class);
		zona = mock(Zona.class);
		//
		this.inspector = new Inspector(sem, zona);
	}

	@Test
	void testSeObtieneLaZona() {
		assertEquals(zona, inspector.getZona());
	}
	
	@Test
	void elInspectorPreguntaSiLaPatendeDadaPoseeEstacionamientoVigente() {
		String patente = new String("AAA111");
		inspector.poseeEstacionamientoVigente(patente);
		verify(sem).poseeEstacionamientoVigente(patente);	
	}

	@Test
	void elInspectorGeneraUnaInfraccionSiLaPatenteNoPoseeEstacionamientoVigente() {
		String patente = new String("OOO222");
		when(sem.poseeEstacionamientoVigente(patente)).thenReturn(false);
		inspector.generarInfraccion(patente);
		verify(sem).generarInfraccion(patente, inspector);
	}
}
