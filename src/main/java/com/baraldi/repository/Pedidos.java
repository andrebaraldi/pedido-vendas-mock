package com.baraldi.repository;

import com.baraldi.model.Pedido;
import com.baraldi.model.StatusPedido;

public class Pedidos {

	// Aqui seria o EntityManager do JPA
	public void guardar(Pedido pedido) {
		System.out.println("Salvando no banco de dados...");
	}
	
	public Pedido buscarPeloCodigo(Long codigo) {
		
		// Aqui retornaria algo do BD
		Pedido pedido = new Pedido();
		
		pedido.setCodigo(codigo);
		pedido.setStatus(StatusPedido.PENDENTE);
		
		return pedido;
	}
}
