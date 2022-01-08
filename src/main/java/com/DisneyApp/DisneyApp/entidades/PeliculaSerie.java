package com.DisneyApp.DisneyApp.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class PeliculaSerie {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	@OneToOne
	private Imagen imagen;
	private String titulo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDeCreacion;
	private Integer calificacion;
	@OneToMany
	private List<Personaje> personajesAsociados;
	
	public PeliculaSerie() {		
	}
	
	public PeliculaSerie(String id, Imagen imagen, String titulo, Date fechaDeCreacion, Integer calificacion,
			List<Personaje> personajesAsociados) {		
		this.id = id;
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaDeCreacion = fechaDeCreacion;
		this.calificacion = calificacion;
		this.personajesAsociados = personajesAsociados;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public List<Personaje> getPersonajesAsociados() {
		return personajesAsociados;
	}

	public void setPersonajesAsociados(List<Personaje> personajesAsociados) {
		this.personajesAsociados = personajesAsociados;
	}			
	
}
