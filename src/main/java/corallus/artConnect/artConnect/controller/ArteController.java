package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.service.ArteService;
import lombok.Getter;



@RestController
@RequestMapping("/arte")
public class ArteController {
	
	@Autowired
	private ArteService arteService;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		try {
			String msg = "Teste do controller";
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Algo deu errado", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/findAll")
	public ResponseEntity<List<Arte>> findAll() {
		try {
			List<Arte> lista = arteService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.FOUND);
		} catch(Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
