package com.DisneyApp.DisneyApp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private PersonajeServicio personajeServicio;
	
	@GetMapping("/")
	private String retornarPersonajes(Model model) {
		model.addAttribute("Search", false);
		model.addAttribute("personajes", personajeServicio.retornarPersonajes() );		
		return "characters.html";
	}
	
	@GetMapping("/search/{nombre}")
	private String retornarPersonajesSearch(Model model, @PathVariable String nombre) {			
		model.addAttribute("Nombre", nombre);
		model.addAttribute("Search", true);
		model.addAttribute("personajes", personajeServicio.retornarPersonajes() );		
		return "characters.html";
	}
	
	@PostMapping("/search/")
	private String redireccionarPersonajesSerach(@RequestParam String nombre) {		
		return "redirect:/characters/search/"+nombre;
	}
	
	@GetMapping("/{id}")
	private String retornarCharacter(Model model, @PathVariable String id) throws Exception {		
		model.addAttribute("Personaje", personajeServicio.retornarPersonajePorId(id));
		return "character.html";
	}	
	
	@GetMapping("/createCharacter")
	private String retornarCreateCharacter(Model model) {
		model.addAttribute("title", "Creación de Personaje");
		return "formPersonaje.html";
	}
	
	@GetMapping("/updateCharacter/{id}")
	private String retornarUpdateCharacter(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("Personaje", personajeServicio.retornarPersonajePorId(id) );
		model.addAttribute("title", "Edición de Personaje");
		return "formPersonaje.html";
	}
	
	@GetMapping("/deleteCharacter/{id}")
	private String redireccionarDeleteCharacter(Model model, @PathVariable String id) throws Exception {
		personajeServicio.borrarPersonaje(id);		
		return "redirect:/characters/";
	}
	
	@PostMapping("/createCharacter")
	private String redireccionarCreateCharacter(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Double peso, @RequestParam String historia, @RequestParam MultipartFile imagen) throws Exception {
		personajeServicio.crearPersonaje(nombre, edad, peso, historia, imagen);
		return "redirect:/characters/";
	}
	
	@PostMapping("/updateCharacter")
	private String redireccionarUpdateCharacter(@RequestParam String id,@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Double peso, @RequestParam String historia, @RequestParam MultipartFile imagen) throws Exception {		
		personajeServicio.editarPersonaje(id, nombre, edad, peso, historia, imagen);
		return "redirect:/characters/";
	}
	
}
