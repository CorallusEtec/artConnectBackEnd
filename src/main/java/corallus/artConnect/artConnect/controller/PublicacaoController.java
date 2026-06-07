package corallus.artConnect.artConnect.controller;

import java.io.IOException;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.request.publicacao.PublicacaoSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.service.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
@Tag(name = "Publicação Controller", description = "Manipula o salvamento de publicações e outras ações pertinentes à publicação.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class PublicacaoController {
    private final PublicacaoService publicacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    /**
     * Realiza uma nova publicação no sistema.
     *
     * @param saveRequest Request com os dados necessários para realizar uma publicação.
     * @return Mensagem caso a publicação tenha sido efetuada com sucesso.
     * @throws IOException Exceção caso ocorra um erro no carregamento do arquivo.
     */
    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicação feita com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<MessageResponse> save(
        PublicacaoSaveRequest saveRequest,
        @AuthenticationPrincipal Usuario usuario
    ) throws Exception {
        MessageResponse msg = this.publicacaoService.save(saveRequest, usuario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Retorna uma lista das publicações feitas no sistema, com paginação e filtragem
     *
     * @param queryFilter Configuração de filtros de busca.
     * @param usuario Referência do usuário autênticado, usado para mostrar a reação
     * desse usuário nas publicações.
     * @param pageable Configurações de paginação.
     * @return Lista paginada com as publicações do sistema.
     *
     * @apiNote Caso o usuaário não esteja autenticado,todos os campos de reação do
     * usuário autênticado estarão {@code null}
     */
    @GetMapping("/findAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
    })
    public ResponseEntity<Page<PublicacaoResponse>> findAll(
            @ParameterObject  PublicacaoFindAllQF queryFilter,
            @AuthenticationPrincipal Usuario usuario,
            @ParameterObject @PageableDefault(size = 7, sort = "id") Pageable pageable
    ) {
        var lista = this.publicacaoService.findAll(queryFilter, usuario, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}

