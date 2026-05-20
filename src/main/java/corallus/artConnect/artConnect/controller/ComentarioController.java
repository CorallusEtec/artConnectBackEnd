package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.request.comentario.ComentarioRequest;
import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.service.ComentarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;


    @PostMapping("/comment")
    public ResponseEntity<String> comment(@RequestBody ComentarioRequest comentario) {
        String msg = this.comentarioService.comentar(comentario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/findByPost/{id}") ResponseEntity<List<ComentarioResponse>> findComments(@PathVariable Long id) {
        List<ComentarioResponse> listaComentario = this.comentarioService.findByPost(id);
        return new ResponseEntity<>(listaComentario, HttpStatus.OK);
    }
}
