package corallus.artConnect.artConnect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.service.ContratanteService;

@RequestMapping("/contratante")
@RestController
public class ContratanteController {
    @Autowired
    private ContratanteService contratanteService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Contratante>> findAll() {
        List<Contratante> lista = this.contratanteService.findAll();
    
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Contratante cadastro) {
        String msg = this.contratanteService.save(cadastro);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    
    @PutMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Contratante contratante) {
    
        String msg = this.contratanteService.edit(contratante); 
        
        if (msg.contains("Erro")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contratante> find(@PathVariable Long id) {
        Contratante contratante = this.contratanteService.findById(id);
        
        if (contratante == null) {
           
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(contratante, HttpStatus.OK);
    }
}