package com.DisneyApp.DisneyApp.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.entidades.Genero;
import com.DisneyApp.DisneyApp.entidades.Imagen;
import com.DisneyApp.DisneyApp.entidades.PeliculaSerie;
import com.DisneyApp.DisneyApp.repositorios.PeliculaSerieRepositorio;

@Service
public class PeliculaSerieServicio {

	@Autowired
	private PeliculaSerieRepositorio PeliculaSerieRepositorio;
		
	@Autowired
	private ImagenServicio imagenServicio;
	
	@Autowired
	private Validaciones validator;
	
	//CREATE
	public void crearPeliculaSerie(String titulo, Date fechaDeCreacion, Integer calificacion, MultipartFile imagen, Genero genero) throws Exception {
		//Valido que los datos no sean nulos salvo la imagen, ya que por defecto si no existe la setteo en nulo (revisar ImagenServicio)
		validator.validarString(titulo, "Titulo");
		validator.validarDate(fechaDeCreacion, "Fecha de Creación");
		validator.validarInteger(calificacion, "Calificación");
		
		
		//Creo un PeliculaSerie. Por el autogenerador de ID no setteo id (revisar entidad PeliculaSerie)
		PeliculaSerie peliculaSerie = new PeliculaSerie();
		//Setteo los datos al PeliculaSerie creado
		peliculaSerie.setTitulo(titulo);
		peliculaSerie.setFechaDeCreacion(fechaDeCreacion);		
		peliculaSerie.setCalificacion(calificacion);
		
		Imagen foto = imagenServicio.guardar(imagen);
		peliculaSerie.setImagen(foto);
		
		if(genero != null) {
			peliculaSerie.setGenero(genero);
		}
		
		//Guardo el PeliculaSerie creado
		PeliculaSerieRepositorio.save(peliculaSerie);
	
	}
	
	//READ
	public PeliculaSerie retornarPeliculaSeriePorId(String id) throws Exception {
		validator.validarString(id, "ID");
		return PeliculaSerieRepositorio.getById(id);
	}
	
	public List<PeliculaSerie> retornarPeliculaSeries(){
		return PeliculaSerieRepositorio.findAll();
	}
	
	public List<PeliculaSerie> retornarPeliculaSeriePorTitulo(String nombre) throws Exception {
		validator.validarString(nombre, "Nombre");
		return PeliculaSerieRepositorio.retornarMoviesPorTitulo(nombre);
	}
	
	public List<PeliculaSerie> retornarPeliculaSeriePorGenero(String genero) throws Exception {
		validator.validarString(genero, "Genero");
		return PeliculaSerieRepositorio.retornarMoviesPorGenero(genero);
	}
	
	public List<PeliculaSerie> retornarPeliculaSeriePorFechaASC() throws Exception {		
		return PeliculaSerieRepositorio.retornarMoviesPorFechaASC();
	}
	
	public List<PeliculaSerie> retornarPeliculaSeriePorFechaDESC() throws Exception {		
		return PeliculaSerieRepositorio.retornarMoviesPorFechaDESC();
	}
	
	//UPDATE
	public void editarPeliculaSerie(String id, String titulo, Date fechaDeCreacion, Integer calificacion, MultipartFile imagen, Genero genero) throws Exception {
		//Valido que los datos no sean nulos salvo la imagen, ya que por defecto si no existe la setteo en nulo (revisar ImagenServicio)
		validator.validarString(id, "ID");
		validator.validarString(titulo, "Titulo");
		validator.validarDate(fechaDeCreacion, "Fecha de Creación");
		validator.validarInteger(calificacion, "Calificación");		
		
		PeliculaSerie peliculaSerie = PeliculaSerieRepositorio.getById(id);
		//Setteo los datos al PeliculaSerie editado
		peliculaSerie.setTitulo(titulo);
		peliculaSerie.setFechaDeCreacion(fechaDeCreacion);		
		peliculaSerie.setCalificacion(calificacion);
		
		Imagen foto = imagenServicio.guardar(imagen);
		peliculaSerie.setImagen(foto);
		
		if(genero != null) {
			peliculaSerie.setGenero(genero);
		}
		
		//Guardo el PeliculaSerie editado
		PeliculaSerieRepositorio.save(peliculaSerie);
	
	}
	
	//DELETE
	public void borrarPeliculaSerie(String id) throws Exception{
		validator.validarString(id, "Id");
		PeliculaSerie peliculaSerie = PeliculaSerieRepositorio.getById(id);		
		PeliculaSerieRepositorio.delete(peliculaSerie);
	}
	
	
}
