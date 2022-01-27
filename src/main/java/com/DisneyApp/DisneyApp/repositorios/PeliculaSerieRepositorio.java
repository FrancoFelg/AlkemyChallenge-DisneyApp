package com.DisneyApp.DisneyApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.PeliculaSerie;

@Repository
public interface PeliculaSerieRepositorio extends JpaRepository<PeliculaSerie, String>{

	@Query("SELECT a FROM PeliculaSerie a")
	public List<PeliculaSerie> retornarMovies();
	
	@Query("SELECT a FROM PeliculaSerie a WHERE a.titulo = :titulo")
	public List<PeliculaSerie> retornarMoviesPorTitulo(@Param("titulo") String titulo);
	
	@Query("SELECT a FROM PeliculaSerie a WHERE a.genero.nombre = :genero")
	public List<PeliculaSerie> retornarMoviesPorGenero(@Param("genero") String genero);
	
	@Query("SELECT a FROM PeliculaSerie a ORDER BY fecha_de_creacion ASC")
	public List<PeliculaSerie> retornarMoviesPorFechaASC();
	
	@Query("SELECT a FROM PeliculaSerie a ORDER BY fecha_de_creacion DESC")
	public List<PeliculaSerie> retornarMoviesPorFechaDESC();		
	
}
