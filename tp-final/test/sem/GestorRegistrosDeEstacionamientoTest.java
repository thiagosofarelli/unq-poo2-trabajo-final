package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroPorCompraPuntual;
import sem.GestorRegistrosDeEstacionamiento;

class GestorRegistrosDeEstacionamientoTest {

	private GestorRegistrosDeEstacionamiento gestor;
	private SistemaEstacionamientoMedido sem;
	
	@BeforeEach
	void setUp() throws Exception {
		sem = mock(SistemaEstacionamientoMedido.class);
		gestor = new GestorRegistrosDeEstacionamiento(sem);
	}

	@Test
	void ElGestorRegistraUnEstacionamientoPuntual() {
		RegistroPorCompraPuntual reg = mock(RegistroPorCompraPuntual.class);
		gestor.registrarEstacionamientoPuntual("AAA111", LocalTime.now(), 5, reg);
		assertTrue(gestor.getRegistrosDeEstacionamiento().containsKey("AAA111"));
	}
	
	@Test
    void ElGestorRegistraUnNuevoEstacionamientoPorApp() throws Exception {
        AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(sem.getPrecioPorHora()).thenReturn(50);
        when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
        when(app.getNumero()).thenReturn(1121334532); //Asigno un numero de telefono
        when(sem.getCredito(1121334532)).thenReturn(90.0f); //Le asigno saldo a ese numero
        when(app.getPatente()).thenReturn("AAA222"); //Le asigno una patente
        gestor.registrarEstacionamientoPorApp(app);
        assertTrue(gestor.getRegistrosDeEstacionamiento().containsKey("AAA222"));
    }
	
	@Test
	void ElGestorRegistraUnaFinalizacionDeEstacionamientoPorApp() throws Exception {
        AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(app.getNumero()).thenReturn(1168889995); //Asigno un numero de telefono
        gestor.registrarEstacionamientoPorApp(app);
        gestor.registrarFinDeEstacionamientoPorApp(app);
        assertFalse(gestor.getRegistrosDePatentePorCelular().containsKey("1168889995"));
	}
	
	@Test
	void ElGestorDeterminaQueUnaPatenteTieneEstacionamientoVigente() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(sem.getPrecioPorHora()).thenReturn(50);
        when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
        when(app.getNumero()).thenReturn(1121334532); //Asigno un numero de telefono
        when(sem.getCredito(1121334532)).thenReturn(90.0f); //Le asigno saldo a ese numero
        when(app.getPatente()).thenReturn("AAA222"); //Le asigno una patente
        gestor.registrarEstacionamientoPorApp(app);
        assertTrue(gestor.poseeEstacionamientoVigente("AAA222"));
	}
	
	 @Test
	 void testGetterRegistrosDeEstacionamiento() {
		 assertEquals(0, gestor.getRegistrosDeEstacionamiento().size());
	    }
	 
	 @Test
	 void testGetterRegistrosDePatentePorCelular() throws Exception {
	     assertEquals(0, gestor.getRegistrosDePatentePorCelular().size());
	    }
	
	
	
	
	}