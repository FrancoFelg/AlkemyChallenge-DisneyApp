package com.DisneyApp.DisneyApp.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="personaje") 
public class Personaje {
	
	//El auto generado me permite no asignar una ID manualmente	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	private String id;
	
	@OneToOne
	private Imagen imagen;
	private String nombre;
	private Integer edad;
	private Double peso;
	private String historia;
	
	@ManyToMany(mappedBy = "personajesAsociados")
	private List<PeliculaSerie> peliculasSeries;
	
	public Personaje() {}

	public Personaje(String id, Imagen imagen, String nombre, Integer edad, Double peso, String historia,
			List<PeliculaSerie> peliculasSeries) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculasSeries = peliculasSeries;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<PeliculaSerie> getPeliculasSeries() {
		return peliculasSeries;
	}

	public void setPeliculasSeries(List<PeliculaSerie> peliculasSeries) {
		this.peliculasSeries = peliculasSeries;
	}

	@Override
	public String toString() {
		return "Personaje [id=" + id + ", imagen=" + imagen + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso
				+ ", historia=" + historia + ", peliculasSeries=" + peliculasSeries + "]";
	}	
	
	
	
}
