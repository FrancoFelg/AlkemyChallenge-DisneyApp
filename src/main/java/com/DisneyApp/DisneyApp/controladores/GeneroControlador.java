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

import com.DisneyApp.DisneyApp.servicios.GeneroServicio;

@Controller
@RequestMapping("/genero")
public class GeneroControlador {

	@Autowired
	private GeneroServicio generoServicio;
	
	@GetMapping("/createGenero")//retorna el formulario
	public String crearGenero(Model model) {
		model.addAttribute("title", "Creación de genero");
		return "genreForm.html";
	}
	
	@GetMapping("/updateGenero/{id}")//retorna el formulario
	public String editarrGenero(Model model, @PathVariable String id) {
		model.addAttribute("genero", generoServicio.retornarGeneroPorId(id));
		model.addAttribute("title", "Edición de genero");
		return "genre.html";
	}
	
	@PostMapping("/createGenero") // Guarda un nuevo genero
	public String redireccionarCreateGenero(@RequestParam String nombre, @RequestParam (required = false) MultipartFile imagen) throws Exception{
		generoServicio.crearGenero(nombre, imagen);
		return "redirect:/movies/";
	}
	
	@PostMapping("/updateGenero/{id}") //Actualiza un genero
	public String redireccionarUpdateGenero(@RequestParam @PathVariable String id, @RequestParam String nombre, @RequestParam (required = false) MultipartFile imagen) throws Exception {
		generoServicio.editarGenero(id, nombre, imagen);
		return "redirect:/movies/";
	}
	
	@GetMapping("/deleteMovie/{id}") //Elimina un genero
	public String redireccionarDeleteMovie(@PathVariable String id) throws Exception {
		generoServicio.borrarGenero(id);
		return "redirect:/movies/";
	}
	
}
