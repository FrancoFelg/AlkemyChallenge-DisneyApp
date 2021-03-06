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

import com.DisneyApp.DisneyApp.entidades.Genero;
import com.DisneyApp.DisneyApp.servicios.GeneroServicio;
import com.DisneyApp.DisneyApp.servicios.PeliculaSerieServicio;

@Controller
@RequestMapping("/movies")
public class PeliculaSeriesControlador {
	
	@Autowired
	private PeliculaSerieServicio movieServicio;
	
	@Autowired
	private GeneroServicio generoServicio;
	
	@GetMapping("/asc") //Se muestran las peliculas
	private String retornarPeliculaSeriesASC(Model model) throws Exception {		
		model.addAttribute("Search", false);
		model.addAttribute("movies", movieServicio.retornarPeliculaSeriePorFechaASC());
		model.addAttribute("generos", generoServicio.retornarGeneros());
		return "movies.html";
	}
	
	@GetMapping("/desc") //Se muestran las peliculas
	private String retornarPeliculaSeriesDESC(Model model) throws Exception {		
		model.addAttribute("Search", false);
		model.addAttribute("movies", movieServicio.retornarPeliculaSeriePorFechaDESC());
		model.addAttribute("generos", generoServicio.retornarGeneros());
		return "movies.html";
	}
	
	@GetMapping("/search/{titulo}") //Muestra los resultados de la busqueda de peliculas
	private String retornarPeliculaSeriePorFechaASCSearch(Model model, 
			@PathVariable (required=false) String titulo,
			@RequestParam (required = false) String genero,
			@RequestParam (required = false) String fecha_de_creacion,
			@RequestParam (required = false) String idMovie) throws Exception {
		if(genero != null|| idMovie != null) {
			
			
			if(idMovie != null) {				
				model.addAttribute("movie", movieServicio.retornarPeliculaSeriePorId(idMovie) );
				return "movie.html";
			}
			if(genero != null) {
				model.addAttribute("Titulo", titulo);
				model.addAttribute("Search", true);
				model.addAttribute("movies", movieServicio.retornarPeliculaSeriePorGenero(genero) );
				return "movies.html";
			}
		}					
		model.addAttribute("Titulo", titulo);
		model.addAttribute("Search", true);
		model.addAttribute("movies", movieServicio.retornarPeliculaSeriePorFechaASC() );		
		return "movies.html";
	}
	
	@PostMapping("/search/") //Redirecciona los datos de la busqueda
	private String redireccionarPersonajesSerach(@RequestParam (required = false)String titulo, 
			@RequestParam (required = false) String genero,
			@RequestParam (required = false) String idMovie) {
		String url = "redirect:/movies/search/";
		if(titulo != null) {
			url += "titulo";
		}		
		if(genero != null || idMovie != null) {
			url += "?";
			
			if(genero != null) {
				url += "genero="+genero;				
			}
			
			if(idMovie != null) {				
				url += "&idMovie="+idMovie;
			}
		}
				
		return url;
	}
	
	@GetMapping("/{id}") //Muestra una pel??cula y sus datos
	private String retornarMovie(Model model, @PathVariable String id) throws Exception {		
		model.addAttribute("Movie", movieServicio.retornarPeliculaSeriePorId(id));		
		return "movie.html";
	}	
	
	@GetMapping("/createMovie") //devuelve el formulario para crear una pelicula
	private String retornarCreateMovie(Model model) {
		model.addAttribute("title", "Creaci??n de movie");
		model.addAttribute("generos", generoServicio.retornarGeneros());
		return "formMovie.html";
	}
	
	@GetMapping("/updateMovie/{id}") //devuelve el formulario para editar una pelicula
	private String retornarUpdateMovie(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("pelicula", movieServicio.retornarPeliculaSeriePorId(id) );
		model.addAttribute("generos", generoServicio.retornarGeneros());
		model.addAttribute("title", "Edici??n de movie");
		return "formMovie.html";
	}
	
	@GetMapping("/deleteMovie/{id}") //Elimina una pel??cula y redirecciona al index
	private String redireccionarDeleteMovie(Model model, @PathVariable String id) throws Exception {
		movieServicio.borrarPeliculaSerie(id);		
		return "redirect:/movies/asc";
	}
	
	@PostMapping("/createMovie") //Crea una pel??cula y redirecciona al index
	private String redireccionarCreateMovie(@RequestParam String titulo, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion, @RequestParam Integer calificacion, MultipartFile imagen, @RequestParam (required = false) Genero genero) throws Exception {
		movieServicio.crearPeliculaSerie(titulo, fechaCreacion, calificacion, imagen, genero);
		return "redirect:/movies/asc";
	}
	
	@PostMapping("/updateMovie") //Edita una pel??cula y redirecciona al index
	private String redireccionarUpdateMovie(@RequestParam String id,@RequestParam String titulo, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCreacion, @RequestParam Integer calificacion, MultipartFile imagen, Genero genero) throws Exception {		
		movieServicio.editarPeliculaSerie(id, titulo, fechaCreacion, calificacion, imagen, genero);
		return "redirect:/movies/asc";
	}
	
	@GetMapping("/moviesAndCharacters/{id}")//Retorna la pagina donde muestra los personajes asociados a la pelicula
	private String retornarMoviesAndCharacters(Model model, @PathVariable String id) throws Exception {
		model.addAttribute("pelicula", movieServicio.retornarPeliculaSeriePorId(id));
		return "moviesAndCharacters.html";
	}
	
	@GetMapping("/order/{orden}")
	public String redireccionarOrden(Model model, @PathVariable String orden) {
		model.addAttribute("orden", orden);
		return "redirect:/movies/{orden}";
	}
	
	
}
