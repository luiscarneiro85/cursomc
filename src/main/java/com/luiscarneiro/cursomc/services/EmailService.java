package com.luiscarneiro.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.luiscarneiro.cursomc.domain.Pedido;

public interface EmailService {

	void SendOrderConfirmationEmail(Pedido obj);
	
	void SendEmail(SimpleMailMessage msg);
}
