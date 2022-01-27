package com.DisneyApp.DisneyApp.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.DisneyApp.DisneyApp.entidades.Usuario;
import com.DisneyApp.DisneyApp.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	//Para más información visitar com.DisneyApp.DisneyApp.servicios.Validaciones.java
	@Autowired
	private Validaciones validator;
	
	@Autowired
	private MyMailSender mailSender;
	
	//CREATE
	public void crearUsuario(String username, String password, String email) throws Exception {
		//Valido que no sean nulas ni vacías
		validator.validarString(username, "Username");
		validator.validarString(password, "Password");
		validator.validarString(email, "Email");
		
		Usuario usuario = new Usuario(); //Creo un nuevo usuario
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);
		
		usuarioRepositorio.save(usuario);
		mailSender.enviarMail(usuario.getUsername(), usuario.getEmail(), 
				"¡Bienvenido a Disney API!");
	}
	
	//UPDATE
	public void modificarUsuario(String id, String username, String password, String email) throws Exception {
		//Valido que no sean nulas ni vacías
		validator.validarString(id, "ID");
		validator.validarString(username, "Username");
		validator.validarString(password, "Password");
		validator.validarString(email, "Email");
		
		Usuario usuario = usuarioRepositorio.getById(id); //Obtengo el usuario por ID
		
		//Setteo los datos
		usuario.setUsername(username); 
		usuario.setPassword(password);
		usuario.setEmail(email);
		
		usuarioRepositorio.save(usuario);
		
	}
	
	//READ
	public Usuario retornarUsuarioPorUsername(String username) {
		return usuarioRepositorio.retornarUsuarioPorUsername(username);
	}
	
	public Usuario retornarUsuarioPorEmail(String email) {
		return usuarioRepositorio.retornarUsuarioPorEmail(email);
	}
	
	//DELETE
	public void eliminarPermanentementeUsuario(String id, String contraseña) throws Exception {
		Usuario usuario = usuarioRepositorio.getById(id);
		
		if(usuario.getPassword().equals(contraseña)) {//Verifico que la contraseña del usuario enviado por ID sea igual a la contraseña enviada
			usuarioRepositorio.delete(usuario); //Si es igual, lo elimino
		}else {
			throw new Exception("No puede eliminar una cuenta que no es suya."); //Si no lo es tiro excepcion
		}
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.retornarUsuarioPorEmail(username);
		if(usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
			
			GrantedAuthority p1 = new SimpleGrantedAuthority("USER");
			permisos.add(p1);

			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("usuariosession", usuario);
			
			User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);
			return user;
		}	
		
		return null;
	}
}
