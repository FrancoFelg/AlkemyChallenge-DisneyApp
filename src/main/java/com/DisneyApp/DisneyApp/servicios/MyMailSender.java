package com.DisneyApp.DisneyApp.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyMailSender {

	@Autowired
	private JavaMailSender MyMailSender;

	@Autowired
	private Validaciones validator;

	public void enviarMail(String nombre, String email, String mensaje) throws Exception {

		try {

			// validations
			validator.validarString(nombre, "Nombre");
			validator.validarString(email, "Email");
			validator.validarString(mensaje, "Mensaje");

			// method

			SimpleMailMessage message1 = new SimpleMailMessage();
			message1.setFrom("examplefelgnoreply@gmail.com");
			message1.setTo("examplefelgnoreply@gmail.com");
			message1.setSubject("Consulta via Web Recibida");
			message1.setText(nombre + ", cuyo mail es " + email + ", envió el siguiente mensaje: " + mensaje);

			MyMailSender.send(message1);

			SimpleMailMessage message2 = new SimpleMailMessage();
			message2.setFrom("examplefelgnoreply@gmail.com");
			message2.setTo(email);
			message2.setSubject("¡Bienvenido a la DisneyAPP!");
			message2.setText("Gracias "+ nombre +" por visitarnos, esperamos que disfrute de nuestra página");
							

			MyMailSender.send(message2);

		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
