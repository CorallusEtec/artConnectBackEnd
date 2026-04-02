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

import corallus.artConnect.artConnect.entity.Contratante;
import corallus.artConnect.artConnect.service.ContratanteService;

@RequestMapping("/contratante")
@RestController
public class ContratanteController {
    @Autowired
    private ContratanteService contratanteService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Contratante>> findAll() {
        List<Contratante> lista = this.contratanteService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Contratante cadastro) {
        String msg = this.contratanteService.save(cadastro);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
