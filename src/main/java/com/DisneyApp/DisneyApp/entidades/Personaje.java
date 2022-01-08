package com.DisneyApp.DisneyApp.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
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
	@ManyToOne
	private PeliculaSerie peliculaSeries;
	
	public Personaje() {}
	
	public Personaje(String id, Imagen imagen, String nombre, Integer edad, Double peso, String historia,
			PeliculaSerie peliculaSeries) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculaSeries = peliculaSeries;
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

	public PeliculaSerie getPeliculaSeries() {
		return peliculaSeries;
	}

	public void setPeliculaSeries(PeliculaSerie peliculaSeries) {
		this.peliculaSeries = peliculaSeries;
	}			
	
}
