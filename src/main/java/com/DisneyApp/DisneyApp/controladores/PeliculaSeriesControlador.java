package com.DisneyApp.DisneyApp.controladores;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.servicios.PeliculaSerieServicio;

@Controller
@RequestMapping("/movies")
public class PeliculaSeriesControlador {
	
	@Autowired
	private PeliculaSerieServicio movieServicio;
	
	@GetMapping("/") //Se muestran las peliculas
	private String retornarPeliculaSeries(Model model) {
		model.addAttribute("Search", false);
		model.addAttribute("movies", movieServicio.retornarPeliculaSeries());	
		return "movies.html";
	}
	
	@GetMapping("/search/{titulo}") //Muestra los resultados de la busqueda de peliculas
	private String retornarPeliculaSeriesSearch(Model model, @PathVariable String titulo) {			
		model.addAttribute("Titulo", titulo);
		model.addAttribute("Search", true);
		model.addAttribute("movies", movieServicio.retornarPeliculaSeries() );		
		return "movies.html";
	}
	
	@PostMapping("/search/") //Redirecciona los datos de la busqueda
	private String redireccionarPersonajesSerach(@RequestParam String titulo) {		
		return "redirect:/movies/search/"+titulo;
	}
	
	@GetMapping("/{id}") //Muestra una película y sus datos
	private String retornarMovie(Model model, @PathVariable String id) throws Exception {		
		model.addAttribute("Movie", movieServicio.retornarPeliculaSeriePorId(id));
		return "movie.html";
	}	
	
	@GetMapping("/createMovie") //devuelve el formulario para crear una pelicula
	private String retornarCreateMovie(Model model) {
		model.addAttribute("title", "Creación de movie");
		return "formMovie.html";
	}
	
	@GetMapping("/updateMovie/{id}") //devuelve el formulario para editar una pelicula
	private String retornarUpdateMovie(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("pelicula", movieServicio.retornarPeliculaSeriePorId(id) );
		model.addAttribute("title", "Edición de movie");
		return "formMovie.html";
	}
	
	@GetMapping("/deleteMovie/{id}") //Elimina una película y redirecciona al index
	private String redireccionarDeleteMovie(Model model, @PathVariable String id) throws Exception {
		movieServicio.borrarPeliculaSerie(id);		
		return "redirect:/movies/";
	}
	
	@PostMapping("/createMovie") //Crea una película y redirecciona al index
	private String redireccionarCreateMovie(@RequestParam String titulo, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion, @RequestParam Integer calificacion, MultipartFile imagen) throws Exception {
		movieServicio.crearPeliculaSerie(titulo, fechaCreacion, calificacion, imagen);
		return "redirect:/movies/";
	}
	
	@PostMapping("/updateMovie") //Edita una película y redirecciona al index
	private String redireccionarUpdateMovie(@RequestParam String id,@RequestParam String titulo, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion, @RequestParam Integer calificacion, MultipartFile imagen) throws Exception {		
		movieServicio.editarPeliculaSerie(id, titulo, fechaCreacion, calificacion, imagen);
		return "redirect:/movies/";
	}
	
	@GetMapping("/moviesAndCharacters/{id}")//Retorna la pagina donde muestra los personajes asociados a la pelicula
	private String retornarMoviesAndCharacters(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("pelicula", movieServicio.retornarPeliculaSeriePorId(id));
		return "moviesAndCharacters.html";
	}
	
	
}
