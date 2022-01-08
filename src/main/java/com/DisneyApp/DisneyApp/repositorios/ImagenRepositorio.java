package com.DisneyApp.DisneyApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DisneyApp.DisneyApp.entidades.Imagen;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{

}