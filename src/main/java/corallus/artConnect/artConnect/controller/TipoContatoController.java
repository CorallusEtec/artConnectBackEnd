package corallus.artConnect.artConnect.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.service.TipoContatoService;

@RestController
@RequestMapping("/tipoContato")
public class TipoContatoController {

    private final TipoContatoService tipoContatoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoContatoController(TipoContatoService tipoContatoService) {
        this.tipoContatoService = tipoContatoService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TipoContatoResponse>> findAll() {
        var lista = this.tipoContatoService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContatoResponse> findById(@PathVariable Long id) {
        var tipoContato = this.tipoContatoService.findById(id);
        return new ResponseEntity<>(tipoContato, HttpStatus.OK);
    }
}
