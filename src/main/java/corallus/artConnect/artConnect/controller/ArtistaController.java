package corallus.artConnect.artConnect.controller;

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
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Artista>> findAll() {
        List<Artista> lista = this.artistaService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Artista artista) {
        String msg = this.artistaService.save(artista);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
