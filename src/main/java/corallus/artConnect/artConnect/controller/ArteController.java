package corallus.artConnect.artConnect.controller;

import java.util.List;

import corallus.artConnect.artConnect.dto.request.arte.ArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.ArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import jakarta.validation.Valid;
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

@RequestMapping("/arte")
@RestController
public class ArteController {

    private final ArteService arteService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ArteController(ArteService arteService) {
        this.arteService = arteService;
    }

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
    public ResponseEntity<MessageResponse> save(@RequestBody @Valid ArteSaveRequest saveRequest) {
        MessageResponse msg = this.arteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> edit(@PathVariable Long id, @RequestBody @Valid ArteEditRequest editRequest) {
        MessageResponse msg = this.arteService.edit(id, editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        MessageResponse msg = this.arteService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
