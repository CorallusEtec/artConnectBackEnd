package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.EstabelecimentoDTO;
import corallus.artConnect.artConnect.service.EstabelecimentoService;
import corallus.artConnect.artConnect.entity.Estabelecimento;

@RestController
@RequestMapping("/parceiros")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	//Endpoint para cadastrar nova conta de estabelecimento
	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastro(@RequestBody EstabelecimentoDTO estabelecimento) {
		try {
			String mensagem = this.estabelecimentoService.cadastro(estabelecimento);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	//Endpoint para testar controller do estabelecimento
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		try {
			String teste = this.estabelecimentoService.teste();
			return new ResponseEntity<>(teste, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Endpoint para procurar todos as contas de estabelecimento cadastradas
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
