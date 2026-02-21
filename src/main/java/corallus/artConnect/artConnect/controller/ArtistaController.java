package corallus.artConnect.artConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		try {
			String msg = "Teste de connectividade";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Algo deu errado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/find")
	public ResponseEntity<Artista> find(@RequestBody String email, @RequestBody String senha) {
		try {
			Artista a = this.artistaService.find(email, senha);
			if(a.equals(null)) { throw new NullPointerException();  }
			return new ResponseEntity<Artista>(a, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
