package corallus.artConnect.artConnect.controller;

import java.util.List;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.dto.request.artista.ArtistaCadastroRequest;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.service.ArtistaService;
import software.amazon.eventstream.Message;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    
    @GetMapping("/findAll")
    public ResponseEntity<List<ArtistaResponse>> findAll(ArtistaFindAllQF filter) {
        List<ArtistaResponse> lista = this.artistaService.findAll(filter);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    
    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody ArtistaCadastroRequest artistaDTO) {
        MessageResponse msg = this.artistaService.save(artistaDTO);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> edit(@PathVariable Long id, @RequestBody @Valid ArtistaEditRequest artistaDTO) {
        MessageResponse msg = this.artistaService.edit(id, artistaDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaResponse> findById(@PathVariable Long id) {
        ArtistaResponse artista = this.artistaService.findById(id);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
}