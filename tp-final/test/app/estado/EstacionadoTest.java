package app.estado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import app.AppEstacionamiento;
import app.asistencia.Activada;
import sem.SistemaEstacionamientoMedido;

class EstacionadoTest {

	private AppEstacionamiento app;
	private SistemaEstacionamientoMedido sem;
	private Activada asistencia;
	private Estacionado estado;
	
	@BeforeEach
	void setUp() throws Exception {
		this.app 		= mock(AppEstacionamiento.class);
		this.sem 		= mock(SistemaEstacionamientoMedido.class);
		this.asistencia = mock(Activada.class);
		this.estado 	= new Estacionado();
	}

	@Test
	void testDriving(){
		when(this.app.getAsistencia()).thenReturn(this.asistencia);
		
		this.estado.driving(this.app);
		
		when(this.app.getEstado()).thenReturn(this.estado);
		verify(this.asistencia, times(1)).driving(this.app);
	}
	
	@Test
	void testSeRegistraUnFinEstacionamiento() {
		when(this.app.getSEM()).thenReturn(this.sem);
		
		this.estado.registrarFinEstacionamiento(this.app);
		
		ArgumentCaptor<Manejando> estadoCaptor = ArgumentCaptor.forClass(Manejando.class);
		verify(this.app, times(1)).setEstado(estadoCaptor.capture());
        assertTrue(estadoCaptor.getValue() instanceof Manejando, "Error test. No se obtuvo una instancia de Manejando en el setEstado()");

		verify(this.sem, times(1)).registrarFinEstacionamientoPorApp(this.app);
	}
}
