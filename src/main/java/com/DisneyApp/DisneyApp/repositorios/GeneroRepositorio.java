package com.DisneyApp.DisneyApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.Genero;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, String>{

	@Query("SELECT a FROM Genero a where a.nombre = :nombre")
	public List<Genero> retornarGenerosPorNombre (@Param("nombre") String nombre);
	
}
