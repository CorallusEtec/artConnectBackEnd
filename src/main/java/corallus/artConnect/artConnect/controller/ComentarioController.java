package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ComentarioFindByPostQF;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.dto.request.comentario.ComentarioRequest;
import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.service.ComentarioService;

@RestController
@RequestMapping("/comentario")
@Tag(name = "Cometário Controller", description = "Controller para as ações relacionadas a comentários de publicações do sistema.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentário publicado."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada.")
    })
    public ResponseEntity<MessageApiResponse> comment(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody @Valid ComentarioRequest comentario
    ) {
        MessageApiResponse msg = this.comentarioService.comentar(usuario, comentario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Busca comentários de uma publicação pelo Id, com paginação e busca com filtros.
     *
     * @param id Id da publicação de onde serão buscados os comentários
     * @param pageable Configurações de Paginação
     * @param queryFilter Configuração de filtros da busca
     * @param usuario Referência do usuario autenticado.
     * @return Lista paginada com os comentários encontrados da publicação correspondente.
     */
    @GetMapping("/findByPost/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    public ResponseEntity<Page<ComentarioResponse>> findByPostId(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(sort = "dataComentario", direction = Sort.Direction.DESC) Pageable pageable,
            @ParameterObject ComentarioFindByPostQF queryFilter,
            @AuthenticationPrincipal() Usuario usuario) {
        Page<ComentarioResponse> listaComentario = this.comentarioService.findByPost(id, pageable, queryFilter, usuario);
        return new ResponseEntity<>(listaComentario, HttpStatus.OK);
    }


    /** Retorna um comentário especíofico pelo Id.
     *
     * @param id Id do comentário.
     * @param usuario Referência do usuário autenticado.
     * @return Objeto do comentário buscado pelo Id.
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    })
    public ResponseEntity<ComentarioResponse> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario
    ) {
        ComentarioResponse comentario = this.comentarioService.findById(id, usuario);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }


    /** Exclui comentario de autoria do usuario autenticado.
     *
     * @param id Id do comentario.
     * @param usuario Referência do usuário autenticado.
     * @return Mensagem caso o comentario tenha sido excluido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageApiResponse> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario
    ) {
        var msg = this.comentarioService.deleteById(id, usuario);
        return ResponseEntity.ok(msg);
    }
}
