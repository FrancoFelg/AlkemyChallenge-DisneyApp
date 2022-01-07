package com.DisneyApp.DisneyApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

	@Query("SELECT a FROM Usuario a WHERE a.username = :username")
	public Usuario retornarUsuarioPorUsername(@Param("username") String username);
	
}
