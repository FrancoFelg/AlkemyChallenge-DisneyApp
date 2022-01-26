package com.DisneyApp.DisneyApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String>{

	@Query("SELECT a FROM Personaje a")
	public List<Personaje> retornarPersonajes();
	
	@Query("SELECT a FROM Personaje a WHERE a.nombre = :nombre")
	public List<Personaje> retornarPersonajesPorNombre(@Param("nombre") String nombre);
	
	@Query("SELECT a FROM Personaje a WHERE a.edad = :edad")
	public List<Personaje> retornarPersonajesPorEdad(@Param("edad") Integer edad);
	
	@Query("SELECT a FROM Personaje a WHERE a.peso = :peso")
	public List<Personaje> retornarPersonajesPorPeso(@Param("peso") Double peso);
	
}
