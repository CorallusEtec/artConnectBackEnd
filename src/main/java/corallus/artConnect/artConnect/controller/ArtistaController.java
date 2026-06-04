package corallus.artConnect.artConnect.controller;

import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    private final ArtistaService artistaService;

    // INJEÇÃO DE DEPENCÊNCIA
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ArtistaResponse>> findAll(ArtistaFindAllQF filter) {
        List<ArtistaResponse> lista = this.artistaService.findAll(filter);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> edit(@AuthenticationPrincipal Usuario auth, @RequestBody @Valid ArtistaEditRequest editRequest) {
        MessageResponse msg = this.artistaService.edit(auth.getId(), editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaResponse> findById(@PathVariable Long id) {
        ArtistaResponse artista = this.artistaService.findById(id);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
}