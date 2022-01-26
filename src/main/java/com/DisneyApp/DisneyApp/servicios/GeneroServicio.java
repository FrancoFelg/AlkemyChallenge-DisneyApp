package com.DisneyApp.DisneyApp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.entidades.Genero;
import com.DisneyApp.DisneyApp.entidades.Imagen;
import com.DisneyApp.DisneyApp.repositorios.GeneroRepositorio;

@Service
public class GeneroServicio {

	@Autowired
	private GeneroRepositorio generoRepositorio;
	
	@Autowired
	private ImagenServicio imagenServicio;
	
	@Autowired
	private Validaciones validator;
	
	//CREATE
	public void crearGenero(String nombre, MultipartFile imagen) throws Exception{
		validator.validarString(nombre, "Nombre");
		
		Genero genero = new Genero();
		genero.setNombre(nombre);
		Imagen foto = imagenServicio.guardar(imagen);
		genero.setImagen(foto);
		
		generoRepositorio.save(genero);
	}
	
	//READ
	public Genero retornarGeneroPorId(String id) {
		return generoRepositorio.getById(id);
	}
	
	public List<Genero> retornarGeneros(){
		return generoRepositorio.findAll();
	}
	
	public List<Genero> retornarGenerosPorNombre(String nombre){
		return generoRepositorio.retornarGenerosPorNombre(nombre);
	}
	
	//UPDATE
	public void editarGenero(String id, String nombre, MultipartFile imagen) throws Exception{
		validator.validarString(id, "ID");
		validator.validarString(nombre, "Nombre");
		
		Genero genero = generoRepositorio.getById(id);
		genero.setNombre(nombre);
		Imagen foto = imagenServicio.guardar(imagen);
		genero.setImagen(foto);
		
		generoRepositorio.save(genero);
	}
	
	//DELETE
	public void borrarGenero(String id) throws Exception{
		validator.validarString(id, "ID");
		Genero genero = generoRepositorio.getById(id);
		generoRepositorio.delete(genero);
	}
	
}
