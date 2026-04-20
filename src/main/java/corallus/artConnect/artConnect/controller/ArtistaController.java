package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.dto.ArtistaCadastroDTO;

import corallus.artConnect.artConnect.dto.ArtistaDTO;
import corallus.artConnect.artConnect.dto.ArtistaEditDTO;

import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    
    @GetMapping("/findAll")
    public ResponseEntity<List<ArtistaDTO>> findAll() {
        List<ArtistaDTO> lista = this.artistaService.findAll();
       
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ArtistaCadastroDTO artistaDTO) {
        String msg = this.artistaService.save(artistaDTO);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody ArtistaEditDTO artistaDTO) {

        String msg = this.artistaService.edit(id, artistaDTO); 
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> find(@PathVariable Long id) {
        ArtistaDTO artista = this.artistaService.findById(id);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
}