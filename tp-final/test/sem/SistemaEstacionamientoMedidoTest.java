package sem;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AppEstacionamiento;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroCargaDeCredito;
import registroDeCompra.RegistroDeCompra;
import registroDeInfraccion.RegistroDeInfraccion;
import suscriptor.Suscriptor;

public class SistemaEstacionamientoMedidoTest {
	private SistemaEstacionamientoMedido sem;
	private GestorRegistrosDeEstacionamiento gestor;
	
	@BeforeEach
	void setUp() {
		this.gestor = mock(GestorRegistrosDeEstacionamiento.class);
		this.sem = new SistemaEstacionamientoMedido(40, LocalTime.of(7,0), LocalTime.of(20,0, 0), this.gestor);
	}
	
	@Test
	void getPrecioPorHoraTest() {
		assertEquals(40, sem.getPrecioPorHora());
	}
	
	@Test
	void getRegistrosDeCompraTest() {
		assertEquals(new ArrayList<RegistroDeCompra>(), sem.getRegistrosDeCompra());
	}
	
	@Test
	void getRegistrosDeInfraccionTest() {
		assertEquals(new ArrayList<RegistroDeInfraccion>(), sem.getInfracciones());
	}
	
	@Test
	void getSuscriptoresTest() {
		assertEquals(new ArrayList<Suscriptor>(), sem.getSuscriptores());
	}
	
	@Test
	void registroCargaSinElNumeroRegistradoYLosAsocia() {
		PuntoDeVenta punto = mock(PuntoDeVenta.class);
		
		sem.registrarCargaDeCredito(punto, 12345678, 500, 4);
		assertEquals(500, sem.getCredito(12345678));
	}
	
	@Test
	void registroCargaConElNumeroRegistradoYLaActualiza() {
		PuntoDeVenta punto = mock(PuntoDeVenta.class);
		
		sem.registrarCargaDeCredito(punto, 87654321, 0, 5);
		sem.registrarCargaDeCredito(punto, 87654321, 300, 6);
		assertEquals(300, sem.getCredito(87654321));
	}
	
	@Test
	void getZonasTest() {
		assertEquals(new ArrayList<Zona>(), sem.getZonas());
	}
	
	@Test
	void getHoraInicioTest() {
		assertEquals(LocalTime.of(7, 0), sem.getHoraInicio());
	}
	
	@Test
	void getHoraFinTest() {
		assertEquals(LocalTime.of(20, 0), sem.getHoraFin());
	}
	
	@Test
	void seDebitaCreditoDeNumeroRegistrado() throws Exception{
		PuntoDeVenta punto = mock(PuntoDeVenta.class);
		
		sem.registrarCargaDeCredito(punto, 12345678, 500, 4);
		sem.debitarCredito(200, 12345678);
		
		assertEquals(300, sem.getCredito(12345678));
	}
	
	@Test
	void seDebitaCreditoDeNumeroNoRegistrado() throws Exception{		
		assertThrows(Exception.class, () -> sem.debitarCredito(200, 12345678));
	}
	
	@Test
	void seRegistraCompraPuntual() {
		PuntoDeVenta punto = mock(PuntoDeVenta.class);
		
		sem.registrarCompraPuntual(punto, "ABC124", 4, 6);
		assertFalse(sem.getRegistrosDeCompra().isEmpty());
	}
	
	@Test
	void seRegistraEstacionamientoPorApp() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
		
		sem.registrarEstacionamientoPorApp(app);
		verify(gestor).registrarEstacionamientoPorApp(app);
	}
	
	@Test
	void seRegistraFinDeEstacionamientoPorApp() throws Exception {
		AppEstacionamiento app = mock(AppEstacionamiento.class);
		
		sem.registrarFinEstacionamientoPorApp(app);
		verify(gestor).registrarFinDeEstacionamientoPorApp(app);
	}
	
	@Test
	void sePreguntaSiUnaPatenteTieneEStacionamientoVigente() {
		sem.poseeEstacionamientoVigente("BCA352");
		verify(gestor).poseeEstacionamientoVigente("BCA352");
	}
	
	@Test
	void seRegistraUnaInfraccion() {
		Inspector inspector = mock(Inspector.class);
		sem.generarInfraccion("FGH638", inspector);
		
		assertFalse(sem.getInfracciones().isEmpty());
	}
	
	@Test
	void seSuscribeAUnaEntidad() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		sem.suscribir(suscriptor);
		
		assertFalse(sem.getSuscriptores().isEmpty());
	}
	
	@Test
	void seDesuscribeAUnaEntidad() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		sem.suscribir(suscriptor);
		sem.desuscribir(suscriptor);
		
		assertTrue(sem.getSuscriptores().isEmpty());
	}
	
	@Test
	void seNotificaDelInicioDeEstacionamiento(){
		Suscriptor suscriptor = mock(Suscriptor.class);
		Estacionamiento estacionamiento= mock(Estacionamiento.class);
		sem.suscribir(suscriptor);
		
		sem.notificarInicioEstacionamiento(estacionamiento);
		verify(suscriptor).actualizarInicioEstacionamiento(sem, estacionamiento);
	}
	
	@Test
	void seNotificaDelFinDeEstacionamiento(){
		Suscriptor suscriptor = mock(Suscriptor.class);
		Estacionamiento estacionamiento= mock(Estacionamiento.class);
		sem.suscribir(suscriptor);
		
		sem.notificarFinEstacionamiento(estacionamiento);
		verify(suscriptor).actualizarFinEstacionamiento(sem, estacionamiento);
	}
	
	@Test
	void seNotificaDeLaCargaDeCredito(){
		Suscriptor suscriptor = mock(Suscriptor.class);
		RegistroCargaDeCredito registro = mock(RegistroCargaDeCredito.class);
		sem.suscribir(suscriptor);
		
		sem.notificarRecargaDeCredito(registro);
		verify(suscriptor).actualizarRecargaDeCredito(sem, registro);
	}
	
	@Test 
	void seFinalizanLosEstacionamientosPorFinDeFranjaHoraria(){
		sem.finalizarEstacionamientosPorFinDeFranjaHoraria();
		
		verify(gestor).finalizarEstacionamientosPorFinDeFranjaHoraria();
	}
}
