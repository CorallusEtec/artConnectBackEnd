package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.EstabelecimentoDTO;
import corallus.artConnect.artConnect.service.EstabelecimentoService;

@RestController
@RequestMapping("/parceiros")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		try {
			String teste = this.estabelecimentoService.teste();
			return new ResponseEntity<>(teste, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<EstabelecimentoDTO>> findAll() {
		try {
			List<EstabelecimentoDTO> lista = estabelecimentoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.FOUND);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}
}
