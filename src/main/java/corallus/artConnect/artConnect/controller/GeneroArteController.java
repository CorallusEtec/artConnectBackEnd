package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.arte.GeneroArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import corallus.artConnect.artConnect.service.GeneroArteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/generoArte")
@RestController
public class GeneroArteController {

    private final GeneroArteService generoArteService;

    public GeneroArteController(GeneroArteService generoArteService) {
        this.generoArteService = generoArteService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<GeneroArte>> findAll() {
        List<GeneroArte> lista = this.generoArteService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody @Valid GeneroArteSaveRequest saveRequest) {
        MessageResponse msg = this.generoArteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}