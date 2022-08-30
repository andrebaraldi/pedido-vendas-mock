package com.baraldi.model.builder;

import com.baraldi.model.Cliente;
import com.baraldi.model.Pedido;

public class PedidoBuilder {
	
	private Pedido instancia;

	// Construtor
	public PedidoBuilder() {
		this.instancia = new Pedido();
	}
	
	// build()...
	public Pedido construir() {
		return instancia;
	}
	
	public PedidoBuilder comValor(double valor) {
		instancia.setValor(valor);
		return this;
	}
	
	public PedidoBuilder para(String nome, String email, String telefone) {
		
		Cliente cliente = new Cliente(nome, email, telefone);
		
		// Seta o cliente do pedido 
		instancia.setCliente(cliente);
		
		return this;
	}

}
