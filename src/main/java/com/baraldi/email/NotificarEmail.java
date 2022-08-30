package com.baraldi.email;

import com.baraldi.model.Pedido;
import com.baraldi.service.AcaoLancamentoPedido;

public class NotificarEmail implements AcaoLancamentoPedido {

	public void executar(Pedido pedido) {
		System.out.println("Enviando o email...");
	}

	//public void enviar(Pedido pedido) {
	//	System.out.println("Enviando o email...");
	//}
	
	

}
