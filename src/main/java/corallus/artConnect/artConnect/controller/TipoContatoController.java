package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TipoContatoService tipoContatoService;


    @GetMapping("/findAll")
    public ResponseEntity<List<TipoContatoResponse>> findAll() {
        var lista = this.tipoContatoService.findAll();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TipoContatoResponse> find(@PathVariable Long id) {
        var tipoContato = this.tipoContatoService.find(id);
        return new ResponseEntity<>(tipoContato, HttpStatus.OK);
    }
}
