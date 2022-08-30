package com.baraldi.sms;

import com.baraldi.model.Pedido;
import com.baraldi.service.AcaoLancamentoPedido;

public class NotificarSMS implements AcaoLancamentoPedido {

	
	//public void notificar(Pedido pedido) {
	//	System.out.println("Enviando o email...");
	//}

	public void executar(Pedido pedido) {
		System.out.println("Enviando o email...");		
	}
	
}
