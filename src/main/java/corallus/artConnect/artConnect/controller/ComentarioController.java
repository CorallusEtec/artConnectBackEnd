package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ComentarioFindByPostQF;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/comment")
    public ResponseEntity<MessageResponse> comment(@AuthenticationPrincipal Usuario u, @RequestBody @Valid ComentarioRequest comentario) {
        MessageResponse msg = this.comentarioService.comentar(u.getId(), comentario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/findByPost/{id}") ResponseEntity<Page<ComentarioResponse>> findComments(
            @PathVariable Long id,
            @PageableDefault(sort = "id") Pageable pageable,
            ComentarioFindByPostQF queryFilter) {
        Page<ComentarioResponse> listaComentario = this.comentarioService.findByPost(id, pageable, queryFilter);
        return new ResponseEntity<>(listaComentario, HttpStatus.OK);
    }
}
