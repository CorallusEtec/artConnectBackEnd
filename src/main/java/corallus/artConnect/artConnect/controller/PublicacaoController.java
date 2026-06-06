package corallus.artConnect.artConnect.controller;

import java.io.IOException;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.service.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
@Tag(name = "Publicação Controller", description = "Manipula o salvamento de publicações e outras ações pertinentes à publicação.")
public class PublicacaoController {
    private final PublicacaoService publicacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    /**
     * Realiza uma nova publicação no sistema.
     *
     * @param legenda Legenda da publicação.
     * @param arquivo Referência do arquivo da publicação.
     * @param tipoMidia Tipo de arquivo da publicação.
     * @param usuario Referência do usuário autênticado, autor da publicação.
     * @return Mensagem caso a publicaão tenha sido efeutada com sucesso.
     * @throws IOException Exceção caso ocorra um erro no carregamento do arquivo.
     */
    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(
        @RequestPart(value = "legenda", required = false) String legenda,
        @RequestPart(value = "file", required = false) MultipartFile arquivo,
        @RequestPart(value = "tipoMidia", required=false) String tipoMidia,
        @AuthenticationPrincipal Usuario usuario
    ) throws IOException {
        MessageResponse msg = this.publicacaoService.save(legenda, arquivo, tipoMidia, usuario);
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
    public ResponseEntity<Page<PublicacaoResponse>> findAll(
            @ParameterObject  PublicacaoFindAllQF queryFilter,
            @AuthenticationPrincipal Usuario usuario,
            @ParameterObject @PageableDefault(size = 7, sort = "id") Pageable pageable
    ) {
        var lista = this.publicacaoService.findAll(queryFilter, usuario, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}

