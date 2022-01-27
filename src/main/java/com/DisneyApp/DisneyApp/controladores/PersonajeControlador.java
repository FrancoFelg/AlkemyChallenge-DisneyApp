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

import com.DisneyApp.DisneyApp.entidades.Personaje;
import com.DisneyApp.DisneyApp.servicios.PeliculaSerieServicio;
import com.DisneyApp.DisneyApp.servicios.PersonajeServicio;

@Controller
@RequestMapping("/characters")
public class PersonajeControlador {
	
	@Autowired
	private PersonajeServicio personajeServicio;
	
	@Autowired
	private PeliculaSerieServicio movieServicio;
	
	@GetMapping("/")
	private String retornarPersonajes(Model model) {
		model.addAttribute("Search", false);
		model.addAttribute("personajes", personajeServicio.retornarPersonajes() );		
		return "characters.html";
	}
	
	@GetMapping("/search/{nombre}")
	private String retornarPersonajesSearch(Model model, @PathVariable String nombre, 
			@RequestParam (required = false) Integer edad, 
			@RequestParam (required = false) Double peso) throws Exception {			
		if(edad != null) {
			model.addAttribute("Nombre", nombre);
			model.addAttribute("Search", true);
			model.addAttribute("personajes", personajeServicio.retornarPersonajePorEdad(edad) );
			return "characters.html";
		}
		if(peso != null) {
			model.addAttribute("Nombre", nombre);
			model.addAttribute("Search", true);
			model.addAttribute("personajes", personajeServicio.retornarPersonajePorPeso(peso) );
			return "characters.html";
		}
		model.addAttribute("Nombre", nombre);
		model.addAttribute("Search", true);
		model.addAttribute("personajes", personajeServicio.retornarPersonajes() );		
		return "characters.html";
	}
	
	@PostMapping("/search/")
	private String redireccionarPersonajesSerach(@RequestParam String nombre, 
			@RequestParam (required = false) Integer edad,
			@RequestParam (required = false) Double peso) {
		String url = "redirect:/characters/search/"+nombre;
		if(edad != null || peso != null) {
			url += "?";
		}
		if(edad != null) {
			url+= "edad="+edad;
		}
		if(peso != null) {
			if(edad != null) {
				url += "&";
			}
			url += "peso="+peso;
		}
		return url;
	}
	
	@GetMapping("/{id}")
	private String retornarCharacter(Model model, @PathVariable String id) throws Exception {
		Personaje personaje = personajeServicio.retornarPersonajePorId(id);
		model.addAttribute("Personaje", personaje);
		model.addAttribute("moviesPersonaje", personaje.getPeliculasSeries());
		System.out.println("Las peliculas del personaje son: " + personaje.getPeliculasSeries());
		return "character.html";
	}	
	
	@GetMapping("/createCharacter")
	private String retornarCreateCharacter(Model model) {
		model.addAttribute("title", "Creación de Personaje");
		model.addAttribute("movies", movieServicio.retornarPeliculaSeries());
		return "formPersonaje.html";
	}
	
	@GetMapping("/updateCharacter/{id}")
	private String retornarUpdateCharacter(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("Personaje", personajeServicio.retornarPersonajePorId(id) );
		model.addAttribute("title", "Edición de Personaje");
		model.addAttribute("movies", movieServicio.retornarPeliculaSeries());
		
		return "formPersonaje.html";
	}
	
	@GetMapping("/deleteCharacter/{id}")
	private String redireccionarDeleteCharacter(Model model, @PathVariable String id) throws Exception {
		personajeServicio.borrarPersonaje(id);		
		return "redirect:/characters/";
	}
	
	@PostMapping("/createCharacter")
	private String redireccionarCreateCharacter(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Double peso, @RequestParam String historia, @RequestParam (required = false) MultipartFile  imagen, @RequestParam (required = false)  String idMovie) throws Exception {
		personajeServicio.crearPersonaje(nombre, edad, peso, historia, imagen, idMovie);
		return "redirect:/characters/";
	}
	
	@PostMapping("/updateCharacter")
	private String redireccionarUpdateCharacter(@RequestParam String id,@RequestParam String nombre, @RequestParam Integer edad, @RequestParam Double peso, @RequestParam String historia, @RequestParam (required = false) MultipartFile imagen, @RequestParam (required = false) String idMovie) throws Exception {		
		personajeServicio.editarPersonaje(id, nombre, edad, peso, historia, imagen, idMovie);
		System.out.println("En POSTMAPPING UPDATE CHARACTER: idMovie="+idMovie);
		return "redirect:/characters/";
	}
	
}
