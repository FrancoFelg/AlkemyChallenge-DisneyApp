package com.DisneyApp.DisneyApp.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.entidades.Imagen;
import com.DisneyApp.DisneyApp.repositorios.ImagenRepositorio;

@Service
public class ImagenServicio {
	
	@Autowired
	private ImagenRepositorio ImagenRepositorio;
	
	public Imagen guardar(MultipartFile image) throws Exception{
		if(image != null) { //En caso de que la imagen no sea nula
			try {
				Imagen Imagen = new Imagen(); //Genero un nuevo objeto (id única)
				//Setteo sus atributos
				Imagen.setMime(image.getContentType());
				Imagen.setName(image.getName());
				Imagen.setContent(image.getBytes());
				
				return ImagenRepositorio.save(Imagen);//Devuelvo la imágen que guardo
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null; //Si es nula retorna nulo
	}
	
	public Imagen actualizar(String idImagen, MultipartFile image) throws Exception {
		if(image != null) { //En caso de que la imagen no sea nula
			try {
				Imagen Imagen = new Imagen(); //Genero un nuevo objeto (id única)
				
				if(idImagen != null) { //Si la id no es nula
					Optional<Imagen> respuesta = ImagenRepositorio.findById(idImagen); //Obtengo un objeto buscado por id desde el repositorio, si no, devuelve null
					if(respuesta.isPresent()) { //Si la respuesta no es nula
						Imagen = respuesta.get(); //El objeto Imagen ahora es igual a la Imagen encontrada por id
					}
				}
				
				//Setteo sus atributos
				Imagen.setMime(image.getContentType());
				Imagen.setName(image.getName());
				Imagen.setContent(image.getBytes());
				
				return ImagenRepositorio.save(Imagen);//Devuelvo la imágen que guardo
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return null; //Si es nula retorna nulo
	}
	
}
