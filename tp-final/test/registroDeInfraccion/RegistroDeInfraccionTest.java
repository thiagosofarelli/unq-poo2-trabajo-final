package registroDeInfraccion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.Inspector;
import sem.Zona;

class RegistroDeInfraccionTest {

	private Inspector inspector;
	private Zona zona;
	private RegistroDeInfraccion registro;
	
	@BeforeEach
	void setUp() throws Exception {
		this.inspector = mock(Inspector.class);
		this.zona 	   = mock(Zona.class);
		this.registro  = new RegistroDeInfraccion(LocalDate.now(), LocalTime.now(), this.zona, this.inspector, "TS 1989");
	}

	@Test
	void testGetterFecha() {
		assertEquals(LocalDate.now(), this.registro.getFecha());
	}

	@Test
	public void testGetterHora() {
		assertEquals(LocalTime.now(), this.registro.getHora());
	}

	@Test
	public void testGetterZona() {
		assertEquals(this.zona, this.registro.getZona());
	}

	@Test
	public void testGetterInspector() {
		assertEquals(this.inspector, this.registro.getInspector());
	}

	@Test
	public void testGetterPatente() {
		assertEquals("TS 1989", this.registro.getPatente());
	}
}
