package app.asistencia;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;
import app.modo.Manual;

class AsistenciaDesactivadaTest {

	private AppEstacionamiento app;
	private Manual modoManual;
	private Desactivada asistencia;

	@BeforeEach
	void setUp() throws Exception {
		this.app 		= mock(AppEstacionamiento.class);
		this.modoManual = mock(Manual.class);
		this.asistencia = new Desactivada();
	}

	@Test
	void testWalking() {
		
		when(this.app.getModo()).thenReturn(this.modoManual);
		
		this.asistencia.walking(this.app);
		
		verify(this.app, never()).recibirNotificacion("Se ha detectado un cambio de desplazamiento de auto a pie");
		verify(this.modoManual, never()).recibirAlertaInicioEstacionamiento(this.app);
	}
	
	@Test
	void testDriving() {
		
		when(this.app.getModo()).thenReturn(this.modoManual);
		
		this.asistencia.driving(this.app);
		
		verify(this.app, never()).recibirNotificacion("Se ha detectado un cambio de desplazamiento de pie a auto");
		verify(this.modoManual, never()).recibirAlertaFinEstacionamiento(this.app);
	}
}
