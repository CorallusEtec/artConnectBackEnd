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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.service.ArtistaService;
import corallus.artConnect.artConnect.service.ContatoArtistaService;
import corallus.artConnect.artConnect.entity.ContatoArtista;

@RestController
@RequestMapping("/artista")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtistaController {
    
    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private ContatoArtistaService contArtistaService;


    // Endpoint para cadastrar um novo artista
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody Artista artista) {
        System.out.println(">>> NOME: " + artista.getNome());
        try {
            String msg = this.artistaService.cadastro(artista);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
    @GetMapping("/{id}")
    public ResponseEntity<Artista> findById(@PathVariable Long id) {
    	try {
    		Artista artista = this.artistaService.findById(id);
    		return new ResponseEntity<>(artista, HttpStatus.FOUND);
    	} catch (Exception e) {
    		return new ResponseEntity<Artista>(new Artista(), HttpStatus.BAD_REQUEST);
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

            if(msg.contains("Senha de artista alterada")) {
                return new ResponseEntity<>(msg, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //endpoint para alterar um artista
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterar(@PathVariable Long id, @RequestBody Artista artista) {
        try {
            String msg = artistaService.alterarArtista(id, artista);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //ENDPOINTS DE CONTATOS DO ARTISTA
    //criar contato do artista
    @PostMapping("/criar-contato/{id}")
    public ResponseEntity<String> cadastrarContato(@PathVariable Long id, @RequestBody ContatoArtista contato) {
        try {
            contato.setIdArtista(id);
            String msg = contArtistaService.cadastrarContato(contato);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Alterar contato
    @PutMapping("/alterar-contato/{id}")
    public ResponseEntity<String> alterarContato(@PathVariable Long id, @RequestBody ContatoArtista contArtistaAlterado){
        try {
    		String msg = this.contArtistaService.alterarContato(id, contArtistaAlterado);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    //Deletar contato
    @DeleteMapping("/deletar-contato/{id}")
    public ResponseEntity<String> deletarContato(@PathVariable Long id) {
    	try {
    		String msg = this.contArtistaService.deletarContato(id);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
			return new ResponseEntity<>("Erro"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping("/{idArtista}/todos")
    public ResponseEntity<List<ContatoArtista>> findByIdArtista(@PathVariable Long idArtista) {
        try {
            List<ContatoArtista> contatos = this.contArtistaService.findByIdArtista(idArtista);
            return new ResponseEntity<>(contatos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
