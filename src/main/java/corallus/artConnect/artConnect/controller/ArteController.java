package corallus.artConnect.artConnect.controller;

import java.util.List;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
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

import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.service.ArteService;

@RequestMapping("/arte")
@RestController
public class ArteController {
    @Autowired
    private ArteService arteService;

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
    public ResponseEntity<MessageResponse> save(@RequestBody Arte arte) {
        MessageResponse msg = this.arteService.save(arte);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> edit(@PathVariable Long id, @RequestBody Arte arte) {
        MessageResponse msg = this.arteService.edit(id, arte);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        MessageResponse msg = this.arteService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
