package com.baraldi.service;

import java.util.List;

import com.baraldi.email.NotificarEmail;
import com.baraldi.model.Pedido;
import com.baraldi.model.StatusPedido;
import com.baraldi.repository.Pedidos;
import com.baraldi.sms.NotificarSMS;
import com.baraldi.service.AcaoLancamentoPedido;

public class PedidoService {

	// Injetando nosso Repository no construtor de nosso Service
	// ------------------------------------------------------------------------
	private Pedidos pedidos;	
	//private NotificarEmail notificarEmail; 
	//private NotificarSMS notificarSMS;	
	private List<AcaoLancamentoPedido> acoes;
	
	
	public PedidoService (Pedidos pedidos, List<AcaoLancamentoPedido> acoes) {
		this.pedidos = pedidos;
		this.acoes = acoes;
		//this.notificarEmail = notificarEmail;
		//this.notificarSMS = notificarSMS;
	}
	// ------------------------------------------------------------------------
	
	
	// Lançamento de Pedidos
	// -----------------------------------------
	public double lancar(Pedido pedido) {
		
		double imposto = pedido.getValor() * 0.10;
		
		// Salva o Pedido e envia o email/sms
		pedidos.guardar(pedido);
		
		// Varre a lista de ações (JAVA 7)
		for ( AcaoLancamentoPedido acao : this.acoes) {
			acao.executar(pedido);
		}		
		// this.acoes.forEach(a -> a.executar(pedido)); JAVA 8
		
		return imposto;		
	}


	// Pagamento de Pedidos
	// -----------------------------------------
	public Pedido pagar(Long codigoPedido) {
	     
		Pedido pedido = pedidos.buscarPeloCodigo(codigoPedido);
		
		if ( !pedido.getStatus().equals(StatusPedido.PENDENTE)) 
			throw new StatusPedidoInvalidoException();
		
		// Se pedido pode ser pago, prossegue
		pedido.setStatus(StatusPedido.PAGO);
		
		return pedido;
	}

}
