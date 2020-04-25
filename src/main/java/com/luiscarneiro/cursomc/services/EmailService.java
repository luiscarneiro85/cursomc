package com.luiscarneiro.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.luiscarneiro.cursomc.domain.Pedido;

public interface EmailService {

	void SendOrderConfirmationEmail(Pedido obj);
	
	void SendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
