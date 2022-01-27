package com.DisneyApp.DisneyApp.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.entidades.Imagen;
import com.DisneyApp.DisneyApp.entidades.PeliculaSerie;
import com.DisneyApp.DisneyApp.entidades.Personaje;
import com.DisneyApp.DisneyApp.repositorios.PeliculaSerieRepositorio;
import com.DisneyApp.DisneyApp.repositorios.PersonajeRepositorio;

@Service
public class PersonajeServicio {

	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	@Autowired
	private PeliculaSerieRepositorio movieRepositorio;
	
	@Autowired
	private ImagenServicio imagenServicio;
	
	@Autowired
	private PeliculaSerieServicio movieServicio;
	
	@Autowired
	private Validaciones validator;
	
	//CREATE
	public void crearPersonaje(String nombre, Integer edad, Double peso, String historia, MultipartFile imagen, String idMovie) throws Exception {
		//Valido que los datos no sean nulos salvo la imagen, ya que por defecto si no existe la setteo en nulo (revisar ImagenServicio)
		validator.validarString(nombre, "Nombre");
		validator.validarInteger(edad, "Edad");
		validator.validarDouble(peso, "Peso");
		validator.validarString(historia, "Historia");
		
		//Creo un personaje. Por el autogenerador de ID no setteo id (revisar entidad Personaje)
		Personaje personaje = new Personaje();
		//Setteo los datos al personaje creado
		personaje.setNombre(nombre);
		personaje.setEdad(edad);
		personaje.setPeso(peso);
		personaje.setHistoria(historia);		
		Imagen foto = imagenServicio.guardar(imagen);
		personaje.setImagen(foto);
		
		personajeRepositorio.save(personaje);
						
	}
	
	//READ
	public Personaje retornarPersonajePorId(String id) throws Exception {
		validator.validarString(id, "ID");
		return personajeRepositorio.getById(id);
	}
	
	public List<Personaje> retornarPersonajes(){
		return personajeRepositorio.findAll();
	}
	
	public List<Personaje> retornarPersonajePorNombre(String nombre) throws Exception {
		validator.validarString(nombre, "Nombre");
		return personajeRepositorio.retornarPersonajesPorNombre(nombre);
	}
	
	public List<Personaje> retornarPersonajePorEdad(Integer edad) throws Exception {
		validator.validarInteger(edad, "Edad");
		return personajeRepositorio.retornarPersonajesPorEdad(edad);
	}
	
	public List<Personaje> retornarPersonajePorPeso(Double peso) throws Exception {
		validator.validarDouble(peso, "Peso");
		return personajeRepositorio.retornarPersonajesPorPeso(peso);
	}
	
	
	//UPDATE
	public void editarPersonaje(String id, String nombre, Integer edad, Double peso, String historia, MultipartFile imagen, String idMovie) throws Exception {
		//Valido que los datos no sean nulos salvo la imagen, ya que por defecto si no existe la setteo en nulo (revisar ImagenServicio)
		validator.validarString(id, nombre);
		validator.validarString(nombre, "Nombre");
		validator.validarInteger(edad, "Edad");
		validator.validarDouble(peso, "Peso");
		validator.validarString(historia, "Historia");
		
		//Creo un personaje. Por el autogenerador de ID no setteo id (revisar entidad Personaje)
		Personaje personaje = personajeRepositorio.getById(id);
		//Setteo los datos al personaje creado
		personaje.setNombre(nombre);
		personaje.setEdad(edad);
		personaje.setPeso(peso);
		personaje.setHistoria(historia);		
		Imagen foto = imagenServicio.guardar(imagen);
		personaje.setImagen(foto);
		PeliculaSerie movie = movieServicio.retornarPeliculaSeriePorId(idMovie);

		List<PeliculaSerie> listaPeliculas = personaje.getPeliculasSeries();
		if(personaje.getPeliculasSeries() == null) {
			listaPeliculas = new ArrayList<>();
		}else {
			listaPeliculas.add(movie);
			personaje.setPeliculasSeries(listaPeliculas);
		}
		
		List<Personaje> listaPersonajes = movie.getPersonajesAsociados();
		if(movie.getPersonajesAsociados() == null) {
			listaPersonajes = new ArrayList<>();
		}else {
			listaPersonajes.add(personaje);
			movie.setPersonajesAsociados(listaPersonajes);
		}
		System.out.println("EN CREAR PERSONAJE: personaje="+personaje);
		System.out.println("EN CREAR PERSONAJE: movie="+movie);
		
		personajeRepositorio.save(personaje);		
	
	}
	
	//DELETE
	public void borrarPersonaje(String id) throws Exception{
		validator.validarString(id, "Id");
		Personaje personaje = personajeRepositorio.getById(id);		
		personajeRepositorio.delete(personaje);
	}
	
	
}
