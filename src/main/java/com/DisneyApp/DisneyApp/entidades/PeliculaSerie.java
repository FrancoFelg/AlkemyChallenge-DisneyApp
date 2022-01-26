package com.DisneyApp.DisneyApp.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="movie") 
public class PeliculaSerie {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	@OneToOne
	private Imagen imagen;
	private String titulo;
	@Temporal(TemporalType.DATE)
	private Date fechaDeCreacion;
	private Integer calificacion;
	
	@ManyToMany
	@JoinTable(
			name="personajes_asociados", //Se va a crear una nueva tabla que una a personaje y 
			joinColumns =  @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "personaje_id")
			)
	private List<Personaje> personajesAsociados;
	
	@ManyToOne
	private Genero genero;
	
	
	
	public PeliculaSerie() {		
	}
	
	

	public PeliculaSerie(String id, Imagen imagen, String titulo, Date fechaDeCreacion, Integer calificacion,
			List<Personaje> personajesAsociados, Genero genero) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaDeCreacion = fechaDeCreacion;
		this.calificacion = calificacion;
		this.personajesAsociados = personajesAsociados;
		this.genero = genero;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}



	@Override
	public String toString() {
		return "PeliculaSerie [id=" + id + ", imagen=" + imagen + ", titulo=" + titulo + ", fechaDeCreacion="
				+ fechaDeCreacion + ", calificacion=" + calificacion + ", personajesAsociados=" + personajesAsociados
				+ ", genero=" + genero + "]";
	}			
	
	
	
}
