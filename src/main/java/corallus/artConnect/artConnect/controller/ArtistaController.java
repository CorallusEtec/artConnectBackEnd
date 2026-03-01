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
        System.out.println(">>> NOME: " + artista.getNome());
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

    //endpoint para troca de senha
    @PutMapping("/troca-senha")
    public ResponseEntity<String> replacePass(@RequestParam Long id, @RequestParam String novaSenha) {
        try {
            String msg = artistaService.replacePass(id, novaSenha);

            if(msg.contains("Sucesso")) {
                return new ResponseEntity<>(msg, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
