package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.service.ArtistaService;

@RestController
@RequestMapping("/artista")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Artista Controller", description = "Controller com as operações específicas do artista")
public class ArtistaController {

    private final ArtistaService artistaService;

    // INJEÇÃO DE DEPENCÊNCIA
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    /**
     * Busca todos os artistas cadastrados no sistema, com paginaçõa e filtragem.
     *
     * @param filter Filtros aplicáveis na busca dos artistas.
     * @param pageable Opções de paginação.
     * @return A lista paginada com os artistas.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<ArtistaResponse>> findAll(
            @ParameterObject
            ArtistaFindAllQF filter,
            @ParameterObject @PageableDefault(sort = "id")
            Pageable pageable
    ) {
        Page<ArtistaResponse> lista = this.artistaService.findAll(filter, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    /**
     * Edição de dados do artista autenticado.
     *
     * @param auth Referência do artista, recebido pela autenticação
     * @param editRequest Request com os dados alterados do artista.
     * @return Mensagem caso os dados tenha sido alterados com sucesso.
     */
    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> edit(
            @AuthenticationPrincipal Usuario auth,
            @RequestBody @Valid ArtistaEditRequest editRequest
    ) {
        MessageResponse msg = this.artistaService.edit(auth.getId(), editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaResponse> findById(@PathVariable Long id) {
        ArtistaResponse artista = this.artistaService.findById(id);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
}