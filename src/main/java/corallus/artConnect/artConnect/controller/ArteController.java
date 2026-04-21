package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.service.ArteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/arte")
@Tag(name = "Arte", description = "Teste")
@RestController
public class ArteController {
    @Autowired
    private ArteService arteService;

    @Operation(summary = "Find All", description = "Busca todas as artes cadastradas no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de artes encontradas")
    @GetMapping("/findAll")
    public ResponseEntity<List<Arte>> findAll() {
        List<Arte> lista = this.arteService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arte> findById(@PathVariable Long id) {
        Arte arte = this.arteService.findById(id);
        return new ResponseEntity<>(arte, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Arte arte) {
        String msg = this.arteService.save(arte);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Arte arte) {
        String msg = this.arteService.edit(id, arte);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String msg = this.arteService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
