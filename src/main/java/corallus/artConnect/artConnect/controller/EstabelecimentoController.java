package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Estabelecimento;
import corallus.artConnect.artConnect.service.EstabelecimentoService;

@RestController
@RequestMapping("/parceiros")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	//Endpoint para cadastrar nova conta de estabelecimento
	@PostMapping("/cadastro")
	public ResponseEntity<String> cadastro(@RequestBody Estabelecimento estabelecimento) {
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
	@GetMapping("/todos")
	public ResponseEntity<List<Estabelecimento>> findAll() {
		try {
			List<Estabelecimento> lista = estabelecimentoService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.FOUND);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/troca-senha")
	public ResponseEntity<String> replacePass(@RequestParam Long id, @RequestParam String novaSenha) {
		try {
			String msg = estabelecimentoService.replacePass(id, novaSenha);

			if(msg.contains("Senha de estabelecimento alterada")) {
				return new ResponseEntity<>(msg, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
