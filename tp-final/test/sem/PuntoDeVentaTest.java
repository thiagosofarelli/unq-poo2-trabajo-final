package sem;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoDeVentaTest {

	private PuntoDeVenta comercio;
	private SistemaEstacionamientoMedido sem;
	
	@BeforeEach
	void setUp() throws Exception {
		// Set up SEM dummy
		sem = mock(SistemaEstacionamientoMedido.class);
		//
		this.comercio = new PuntoDeVenta(sem);
	}

	@Test
	void elComercioRegistraUnaCargaDeCredito() {
		int numero = 1121688341;
		float monto = 40.0f;
		comercio.registrarCargaDeCredito(numero, monto);
		verify(sem).registrarCargaDeCredito(comercio, numero, monto, 0);
	}
	
	@Test
	void elComercioRegistraUnaCompraDeHorasPuntuales() {
		String patente = "AF558JK";
		int horas = 5;
		int nroControlActual = comercio.getNumeroDeControlActual();
		comercio.registrarCompraPuntual(patente, horas);
		verify(sem).registrarCompraPuntual(comercio, patente, horas, nroControlActual);
	}
	
	@Test
	void elComercioAumentaSuNumeroDeControlAlGenerarUnaCargaDeCredito() {
		int numero = 1121688341;
		float monto = 40.0f;
		int nroControlActual = comercio.getNumeroDeControlActual();
		comercio.registrarCargaDeCredito(numero, monto);
		assertEquals(nroControlActual +1 , comercio.getNumeroDeControlActual());
	}
	
	@Test
	void elComercioAumentaSuNumeroDeControlAlRegistrarUnaCargaPuntual() {
		String patente = "AF558JK";
		int horas = 5;
		int nroControlActual = comercio.getNumeroDeControlActual();
		comercio.registrarCompraPuntual(patente, horas);
		assertEquals(nroControlActual +1 , comercio.getNumeroDeControlActual());
	}

}
