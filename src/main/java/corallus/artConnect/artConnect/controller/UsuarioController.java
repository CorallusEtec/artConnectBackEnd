package corallus.artConnect.artConnect.controller;

import java.util.List;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // INJEÇÃO DE DEPENCÊNCIA
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioResponse>> findAll(UsuarioFindAllQF filter) {
        List<UsuarioResponse> lista = usuarioService.findAll(filter);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        UsuarioResponse usuario = this.usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
