package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ComentarioFindByPostQF;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
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
@Tag(name = "Cometário Controller", description = "Controller para as ações relacionadas a comentários de publicações do sistema.")
public class ComentarioController {

    private final ComentarioService comentarioService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    /**
     * Publica um comentário numa publicação do sistema.
     *
     * @param usuario Referência do usuário autenticado que fará o comentario.
     * @param comentario Request com os dados do comentário.
     * @return Mensagem caso o comentário tenha sido publicado.
     */
    @PostMapping("/comment")
    public ResponseEntity<MessageResponse> comment(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody @Valid ComentarioRequest comentario
    ) {
        MessageResponse msg = this.comentarioService.comentar(usuario.getId(), comentario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Busca comentários de uma publicação pelo Id, com paginação e busca com filtros.
     *
     * @param id Id da publicação de onde serão buscados os comentários
     * @param pageable Configurações de Paginação
     * @param queryFilter Configuração de filtros da busca
     * @return Lista paginada com os comentários encontrados da publicação correspondente.
     */
    @GetMapping("/findByPost/{id}") ResponseEntity<Page<ComentarioResponse>> findComments(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(sort = "id") Pageable pageable,
            @ParameterObject ComentarioFindByPostQF queryFilter) {
        Page<ComentarioResponse> listaComentario = this.comentarioService.findByPost(id, pageable, queryFilter);
        return new ResponseEntity<>(listaComentario, HttpStatus.OK);
    }
}
