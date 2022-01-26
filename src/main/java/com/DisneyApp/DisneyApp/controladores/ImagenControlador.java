package com.DisneyApp.DisneyApp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DisneyApp.DisneyApp.entidades.Genero;
import com.DisneyApp.DisneyApp.entidades.PeliculaSerie;
import com.DisneyApp.DisneyApp.entidades.Personaje;
import com.DisneyApp.DisneyApp.servicios.GeneroServicio;
import com.DisneyApp.DisneyApp.servicios.PeliculaSerieServicio;
import com.DisneyApp.DisneyApp.servicios.PersonajeServicio;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

	@Autowired
	private PersonajeServicio personajeServicio;
	
	@Autowired
	private PeliculaSerieServicio movieServicio;
	
	@Autowired
	private GeneroServicio generoServicio;
	
	@GetMapping("/personaje")
	public ResponseEntity<byte[]> fotoPersonaje(@RequestParam String id){
		try {
			Personaje personaje = personajeServicio.retornarPersonajePorId(id);
			byte[] foto = personaje.getImagen().getContent();	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			return new ResponseEntity<>(foto, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/movie")
	public ResponseEntity<byte[]> fotoPelicula(@RequestParam String id){
		try {
			PeliculaSerie movie = movieServicio.retornarPeliculaSeriePorId(id);
			byte[] foto = movie.getImagen().getContent();	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			return new ResponseEntity<>(foto, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/genero")
	public ResponseEntity<byte[]> fotoGenero(@RequestParam String id){
		try {
			Genero genero = generoServicio.retornarGeneroPorId(id);
			byte[] foto = genero.getImagen().getContent();	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			return new ResponseEntity<>(foto, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
