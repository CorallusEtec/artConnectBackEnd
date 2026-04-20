package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Artista>> findAll() {
        List<Artista> lista = this.artistaService.findAll();
       
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Artista artista) {
        String msg = this.artistaService.save(artista);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    

    @PutMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Artista artista) {

        String msg = this.artistaService.edit(artista); 
        
        if (msg.contains("Erro")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Artista> find(@PathVariable Long id) {
        Artista artista = this.artistaService.findById(id);
        if (artista == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
}