package com.DisneyApp.DisneyApp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DisneyApp.DisneyApp.servicios.UsuarioServicio;

@Controller
@RequestMapping("/auth")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioService;
	
	@GetMapping("/login")
	public String retornarLogin() {
		return "login.html";
	}
	
	@GetMapping("/register")
	public String retornarRegister() {
		return "register.html";
	}
	
	@PostMapping("/register") 
	public String redireccionarRegister(@RequestParam String username, @RequestParam String password, @RequestParam String email) throws Exception {
		usuarioService.crearUsuario(username, password, email);
		return "redirect:/";
	}
	
}
