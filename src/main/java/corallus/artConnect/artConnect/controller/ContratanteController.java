package corallus.artConnect.artConnect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteCadastroDTO;
import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteDTO;
import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteEditDTO;
import corallus.artConnect.artConnect.service.ContratanteService;

@RequestMapping("/contratante")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContratanteController {
    @Autowired
    private ContratanteService contratanteService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ContratanteDTO>> findAll() {
        List<ContratanteDTO> lista = this.contratanteService.findAll();
    
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam(name = "tipo", defaultValue = "cpf") String tipo, @RequestBody ContratanteCadastroDTO cadastro) {
        String msg = this.contratanteService.save(tipo, cadastro);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody ContratanteEditDTO contratanteDTO) { 
        String msg = this.contratanteService.edit(id, contratanteDTO); 
        
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratanteDTO> find(@PathVariable Long id) {
        ContratanteDTO contratante = this.contratanteService.findById(id);
        
        return new ResponseEntity<>(contratante, HttpStatus.OK);
    }
}