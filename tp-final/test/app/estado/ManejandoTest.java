package app.estado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import app.AppEstacionamiento;
import app.asistencia.Activada;
import sem.SistemaEstacionamientoMedido;

class ManejandoTest {

	private AppEstacionamiento app;
	private SistemaEstacionamientoMedido sem;
	private Activada asistencia;
	private Manejando estado;
	
	@BeforeEach
	void setUp() throws Exception {
		this.app 		= mock(AppEstacionamiento.class);
		this.sem 		= mock(SistemaEstacionamientoMedido.class);
		this.asistencia = mock(Activada.class);
		this.estado 	= new Manejando();
	}

	@Test
	void testWalking(){
		when(this.app.getAsistencia()).thenReturn(this.asistencia);
		
		this.estado.walking(this.app);
		
		when(this.app.getEstado()).thenReturn(this.estado);
		verify(this.asistencia, times(1)).walking(this.app);
	}
	
	@Test
	void testSeRegistraUnInicioEstacionamiento() {
		when(this.app.getSEM()).thenReturn(this.sem);
		
		this.estado.registrarInicioEstacionamiento(this.app);
		
		ArgumentCaptor<Estacionado> estadoCaptor = ArgumentCaptor.forClass(Estacionado.class);
		verify(this.app, times(1)).setEstado(estadoCaptor.capture());
        assertTrue(estadoCaptor.getValue() instanceof Estacionado, "Error test. No se obtuvo una instancia de Estacionado en el setEstado()");

		verify(this.sem, times(1)).registrarEstacionamientoPorApp(this.app);
	}
}
