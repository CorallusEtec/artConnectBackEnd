package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.tipoContato;
import corallus.artConnect.artConnect.service.tipoContatoService;

@RestController()
@RequestMapping("/tipo-contato")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class tipoContatoController {
	@Autowired
    private tipoContatoService tipoContatoService;

    @GetMapping("/todos")
    public ResponseEntity<List<tipoContato>> findAll() {
        try {
            List<tipoContato> lista = tipoContatoService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/criar-tipo")
    public ResponseEntity<String> criarTipo(@RequestBody tipoContato tipoContato) {
    	try {
    		String msg = this.tipoContatoService.criarTipo(tipoContato);
            return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>("Erro ao cadastrar: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }
}
