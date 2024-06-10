package app.modo;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;

class ModoAutomaticoTest {

	private AppEstacionamiento app;
	private Automatico modo;
		
	@BeforeEach
	void setUp() throws Exception {
		this.app  = mock(AppEstacionamiento.class);
		this.modo = new Automatico();
	}

	@Test
	void testLaAppRecibeUnaAlertaInicioEstacionamiento() {
		this.modo.recibirAlertaInicioEstacionamiento(this.app);
			
		verify(this.app, times(1)).registrarInicioEstacionamiento();
		verify(this.app, times(1)).recibirNotificacion("Se ha realizado un inicio de estacionamiento de forma automática");
	}
	
	@Test
	void testLaAppRecibeUnaAlertaFinEstacionamiento() {
		this.modo.recibirAlertaFinEstacionamiento(this.app);
		
		verify(this.app, times(1)).registrarFinEstacionamiento();
		verify(this.app, times(1)).recibirNotificacion("Se ha realizado un fin de estacionamiento de forma automática");
	}

}
