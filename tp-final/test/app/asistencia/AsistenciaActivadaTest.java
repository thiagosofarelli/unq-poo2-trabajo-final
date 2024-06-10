package app.asistencia;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;
import app.modo.Automatico;

class AsistenciaActivadaTest {
	
	private AppEstacionamiento app;
	private Automatico modoAutomatico;
	private Activada asistencia;

	@BeforeEach
	void setUp() throws Exception {
		this.app 			= mock(AppEstacionamiento.class);
		this.modoAutomatico = mock(Automatico.class);
		this.asistencia 	= new Activada();
	}

	@Test
	void testWalking() {
		
		when(this.app.getModo()).thenReturn(this.modoAutomatico);
		
		this.asistencia.walking(this.app);
		
		verify(this.app, times(1)).recibirNotificacion("Se ha detectado un cambio de desplazamiento de auto a pie");
		verify(this.modoAutomatico, times(1)).recibirAlertaInicioEstacionamiento(this.app);
	}
	
	@Test
	void testDriving() {
		
		when(this.app.getModo()).thenReturn(this.modoAutomatico);
		
		this.asistencia.driving(this.app);
		
		verify(this.app, times(1)).recibirNotificacion("Se ha detectado un cambio de desplazamiento de pie a auto");
		verify(this.modoAutomatico, times(1)).recibirAlertaFinEstacionamiento(this.app);
	}
}
