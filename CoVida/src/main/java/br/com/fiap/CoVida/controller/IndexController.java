package br.com.fiap.CoVida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.CoVida.model.Doador;

@Controller
public class IndexController {
	
	private Doador doador;
	
	@RequestMapping("/index")
	public String iniciar() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String email, String senha) {
		
		if(doador.getEmail() == email && doador.getSenha() == senha) {
			return "contato";
		} else {
			return "login";
		}
		
	}	
	

}
