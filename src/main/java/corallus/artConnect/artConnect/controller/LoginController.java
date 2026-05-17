package corallus.artConnect.artConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.UsuarioLoginDTO;
import corallus.artConnect.artConnect.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/logar") 
	public ResponseEntity<UsuarioLoginDTO> login(@RequestParam String email, @RequestParam String senha) {
			UsuarioLoginDTO usuario = loginService.login(email, senha);
			return ResponseEntity.ok(usuario);
	}
	
}
