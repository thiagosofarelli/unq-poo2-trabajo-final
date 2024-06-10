package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroPorCompraPuntual;

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
    void ElGestorRegistraUnNuevoEstacionamientoPorAppPeroNoTieneCredito() throws Exception {
        AppEstacionamiento app = mock(AppEstacionamiento.class);
        gestor.registrarEstacionamientoPorApp(app);
        verify(app).recibirNotificacion("No tiene saldo suficiente para la compra");
	}
	
	 @Test
	    void testRegistrarEstacionamiento_LanzaExcepcionSiNumeroYaTieneEstacionamiento() throws Exception {
	        AppEstacionamiento app = mock(AppEstacionamiento.class);
	        when(app.getNumero()).thenReturn(1168889995);
	        when(app.getPatente()).thenReturn("AAA222");
	        gestor.getRegistrosDePatentePorCelular().put(app.getNumero(), null);
	        Exception exception = assertThrows(Exception.class, () -> {
	            gestor.registrarEstacionamientoPorApp(app);
	        });
	        assertEquals("El numero ya tiene un estacionamiento vigente", exception.getMessage());
	    }
	
	@Test
	void ElGestorRegistraUnaFinalizacionDeEstacionamientoPorApp() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(sem.getPrecioPorHora()).thenReturn(50);
        when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
        when(app.getNumero()).thenReturn(1121334532); //Asigno un numero de telefono
        when(sem.getCredito(1121334532)).thenReturn(90.0f); //Le asigno saldo a ese numero
        when(app.getPatente()).thenReturn("AAA222"); //Le asigno una patente
        gestor.registrarEstacionamientoPorApp(app);
        gestor.registrarFinDeEstacionamientoPorApp(app);
        assertFalse(gestor.getRegistrosDePatentePorCelular().containsKey(app.getNumero()));
	}
	
	@Test
	void ElGestorNoRegistraUnaFinalizacionDeEstacionamientoPorApp() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(sem.getPrecioPorHora()).thenReturn(50);
        when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
        when(app.getNumero()).thenReturn(1121334532); //Asigno un numero de telefono
        when(sem.getCredito(1121334532)).thenReturn(90.0f); //Le asigno saldo a ese numero
        when(app.getPatente()).thenReturn(null); //Le asigno una patente
        gestor.registrarFinDeEstacionamientoPorApp(app);
        assertFalse(gestor.getRegistrosDePatentePorCelular().containsKey(app.getNumero()));
	}
	
	@Test
	void ElGestorDeterminaQueUnaPatenteTieneEstacionamientoVigenteDependiendoLaHoraDeFin() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(sem.getPrecioPorHora()).thenReturn(50);
        when(sem.getHoraFin()).thenReturn(LocalTime.of(20, 0));
        when(app.getNumero()).thenReturn(1121334532); //Asigno un numero de telefono
        when(sem.getCredito(1121334532)).thenReturn(90.0f); //Le asigno saldo a ese numero
        when(app.getPatente()).thenReturn("AAA222"); //Le asigno una patente
        gestor.registrarEstacionamientoPorApp(app);
        if (LocalTime.now().isBefore(sem.getHoraFin())) {
        assertTrue(gestor.poseeEstacionamientoVigente(app.getPatente()));
        } else {
            assertFalse(gestor.poseeEstacionamientoVigente(app.getPatente()));
        }
	}
	
	@Test
	void ElGestorDeterminaQueUnaPatenteNoTieneEstacionamientoVigente() {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
        when(app.getPatente()).thenReturn("AAA222"); //Le asigno una patente
        assertFalse(gestor.poseeEstacionamientoVigente(app.getPatente()));
	}
	
	 @Test
	 void testGetterRegistrosDeEstacionamiento() {
		 assertEquals(0, gestor.getRegistrosDeEstacionamiento().size());
	 }
	 
	 @Test
	 void testGetterRegistrosDePatentePorCelular() throws Exception {
	     assertEquals(0, gestor.getRegistrosDePatentePorCelular().size());
	 }
	 
	 @Test
	 void elGestorFinalizaTodosLosEstacionamientos() {
		 Estacionamiento est = mock(Estacionamiento.class);
		 gestor.getRegistrosDeEstacionamientoDelDia().put("AAA999", est);
		 gestor.finalizarEstacionamientosPorFinDeFranjaHoraria();
		 verify(est).finalizar(sem);
		 assertTrue(gestor.getRegistrosDeEstacionamientoDelDia().isEmpty());
	 }
}