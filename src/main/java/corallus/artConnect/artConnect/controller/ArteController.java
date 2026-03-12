package corallus.artConnect.artConnect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.service.ArteService;

@RestController()
@RequestMapping("/arte")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArteController {
    @Autowired
    private ArteService arteService;

    @GetMapping("/todos")
    public ResponseEntity<List<Arte>> findAll() {
        try {
            List<Arte> lista = arteService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirArte(@RequestBody Arte arte) {
    	try {
    		String msg = this.arteService.inserirArte(arte);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarArte(@PathVariable Long id, @RequestBody Arte arteAlterada) {
    	try {
    		String msg = this.arteService.alterarArte(id, arteAlterada);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarArte(@PathVariable Long id) {
    	try {
    		String msg = this.arteService.deletarArte(id);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
			return new ResponseEntity<>("Erro"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
}
