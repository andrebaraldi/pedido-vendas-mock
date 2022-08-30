package com.baraldi.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.baraldi.email.NotificarEmail;
import com.baraldi.model.Pedido;
import com.baraldi.model.StatusPedido;
import com.baraldi.model.builder.PedidoBuilder;
import com.baraldi.repository.Pedidos;
import com.baraldi.sms.NotificarSMS;

public class PedidoServiceTest {
	
	//@Mock
	private Pedidos pedidos; 
	
	//@Mock 
	private NotificarEmail email;
	
	//@Mock 
	private NotificarSMS sms;
	
	
	// Injetando o Service no ServiceTest
	// --------------------------------------------------
	private PedidoService pedidoService;
	private Pedido pedido;

	
	@Before
	public void setup() {
				
		//MockitoAnnotations.initMocks(this);
		pedidos = new Pedidos();		
		email = new NotificarEmail();
		sms = new NotificarSMS();
		
		// Criação do pedido e suas ações
		//pedidoService = new PedidoService(pedidos, email, sms);
		List<AcaoLancamentoPedido> acoes = Arrays.asList(email, sms);		
		pedidoService = new PedidoService(pedidos, acoes);	
		
		pedido = new PedidoBuilder()
	            .comValor(100.0)
	            .para("João","joao@jhon.com","999.999")
	            .construir();
	}
	// --------------------------------------------------

	
	@Test
	public void deveCalcularOImposto() throws Exception{
		
		double imposto = pedidoService.lancar(pedido);
		
		assertEquals(10.0, imposto, 0.0001);	
	}
	
	
	@Test
	public void deveSalvarPedidoNoBancoDeDados() throws Exception{
		
		pedidoService.lancar(pedido);
		//Mockito.verify(pedidos).guardar(pedido);
	}
	
	
	@Test
	public void deveNotificarPorEmail() throws Exception {
		pedidoService.lancar(pedido);
		//Mockito.verify(email).executar(pedido);
	}
	
	@Test
	public void deveNotificarPorSMS() throws Exception {
		pedidoService.lancar(pedido);
		//Mockito.verify(sms).executar(pedido);
	}

	
	@Test
	public void devePagarPedidoPendente() throws Exception {
		
		Long codigoPedido = 135l;

		// Usando Mock...
		Pedido pedidoPendente = new Pedido();
		pedidoPendente.setStatus(StatusPedido.PENDENTE);
		// Mock, por favor quando for buscarPeloCodigo, me retorne o pedido acima (pendente)
		Mockito.when(pedidos.buscarPeloCodigo(codigoPedido)).thenReturn(pedidoPendente);
		
		Pedido pedidoPago = pedidoService.pagar(codigoPedido);
		
		// Valida se pedido foi pago (se o status do pedido foi alterado...)
		assertEquals(StatusPedido.PAGO, pedidoPago.getStatus());
	}
	
}
