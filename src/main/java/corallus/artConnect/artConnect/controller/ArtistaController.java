package corallus.artConnect.artConnect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		try {
			String msg = "Teste de connectividade";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Algo deu errado", HttpStatus.BAD_REQUEST);
		}
	}
	
}
