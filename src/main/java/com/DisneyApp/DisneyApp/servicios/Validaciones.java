package com.DisneyApp.DisneyApp.servicios;

import org.springframework.stereotype.Service;

@Service
public class Validaciones {
	
	//Este método recibe una cadena y verifica que no sea ni nula ni vacía
	public void validarString(String contenido, String nombre) throws Exception{
		if(contenido == null || contenido.isEmpty()) {
			throw new Exception(nombre + " can't be null");
		}
	}
	
}