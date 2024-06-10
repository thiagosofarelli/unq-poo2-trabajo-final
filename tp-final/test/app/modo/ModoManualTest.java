package app.modo;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;

class ModoManualTest {

	private AppEstacionamiento app;
	private Manual modo;
		
	@BeforeEach
	void setUp() throws Exception {
		this.app  = mock(AppEstacionamiento.class);
		this.modo = new Manual();
	}

	@Test
	void testLaAppNoRecibeUnaAlertaInicioEstacionamiento() {
		this.modo.recibirAlertaInicioEstacionamiento(this.app);
			
		verify(this.app, never()).registrarInicioEstacionamiento();
		verify(this.app, never()).recibirNotificacion("Se ha realizado un inicio de estacionamiento de forma automática");
	}
	
	@Test
	void testLaAppNoRecibeUnaAlertaFinEstacionamiento() {
		this.modo.recibirAlertaFinEstacionamiento(this.app);
		
		verify(this.app, never()).registrarFinEstacionamiento();
		verify(this.app, never()).recibirNotificacion("Se ha realizado un fin de estacionamiento de forma automática");
	}
}
