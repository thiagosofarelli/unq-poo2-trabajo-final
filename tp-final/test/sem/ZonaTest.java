package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZonaTest {

	private Zona zona;
	private Inspector inspector;
	
	@BeforeEach
	void setUp() throws Exception {
		inspector = mock(Inspector.class);
		zona = new Zona(inspector);
	}

	@Test
	void testGetterDelInspectorDeLaZona() {
		assertEquals(zona.getInspector(), inspector);
	}
}
