package com.DisneyApp.DisneyApp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.repositorios.PersonajeRepositorio;
import com.DisneyApp.DisneyApp.servicios.PersonajeServicio;

@Controller
@RequestMapping("/characters")
public class PersonajeControlador {
	
	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	@Autowired
	private PersonajeServicio personajeServicio;
	
	@GetMapping("/")
	private String retornarPersonajes(Model model) {
		model.addAttribute("personajes", personajeRepositorio.retornarPersonajes());
		
		return "characters.html";
	}
	
	@GetMapping("/createCharacter")
	private String retornarCreateCharacter() {		
		return "formPersonaje.html";
	}
	
	@PostMapping("/createCharacter")
	private String redireccionarCreateCharacter(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Double peso, @RequestParam String historia, @RequestParam MultipartFile imagen) throws Exception {
		personajeServicio.crearPersonaje(nombre, edad, peso, historia, imagen);
		return "redirect:/";
	}
	
}
