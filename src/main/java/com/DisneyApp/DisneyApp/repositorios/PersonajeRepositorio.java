package com.DisneyApp.DisneyApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String>{

	@Query("SELECT a FROM Personaje a")
	public List<Personaje> retornarPersonajes();
	
}