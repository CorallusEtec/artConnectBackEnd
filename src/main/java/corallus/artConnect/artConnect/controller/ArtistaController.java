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
import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
    
    @Autowired
    private ArtistaService artistaService;


    // Endpoint para cadastrar um novo artista
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody Artista artista) {
        try {
            String msg = this.artistaService.cadastro(artista);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // Endpoint para buscar todos os artistas cadastrados
    @GetMapping("/todos")
    public ResponseEntity<List<Artista>> findAll() {
        try {
            List<Artista> artistas = artistaService.findAll();
            return new ResponseEntity<>(artistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint de teste para verificar se o controller está funcionando
    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        try {
            return new ResponseEntity<>("Teste de endpoint do ArtistaController", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
