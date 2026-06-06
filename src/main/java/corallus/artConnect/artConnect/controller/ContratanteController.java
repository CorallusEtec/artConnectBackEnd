package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
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
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteEditRequest;
import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.service.ContratanteService;

@RequestMapping("/contratante")
@RestController
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Contratante Controller", description = "Ações relacionadas aos contratantes do sistema.")
public class ContratanteController {
    private final ContratanteService contratanteService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteController(ContratanteService contratanteService) {
        this.contratanteService = contratanteService;
    }

    /**
     * Retorna uma lista com contratantes do sistema, com paginação e filtros de busca.
     *
     * @param queryFilter Configuração de filtros da busca.
     * @param pageable Configurações de paginação.
     * @return Retorna a lista paginada com os contratantes do sistema.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<ContratanteResponse>> findAll(
            @ParameterObject  ContratanteFindAllQF queryFilter,
            @ParameterObject @PageableDefault(sort = "id") Pageable pageable
    ) {
        Page<ContratanteResponse> lista = this.contratanteService.findAll(queryFilter, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Edita os dados do contratante autenticado
     *
     * @param contratante Referência do contratante autênticado.
     * @param editRequest Request com os dados que serão sobrepostos para este contratante.
     * @return Mensagem caso os dados do contratante tenham sido alterados com sucesso.
     */
    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> edit(
            @AuthenticationPrincipal Usuario contratante,
            @RequestBody @Valid ContratanteEditRequest editRequest
    ) {
        MessageResponse msg = this.contratanteService.edit(contratante.getId(), editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Busca um contratante pelo Id.
     *
     * @param id Id do contratante que será buscado.
     * @return O objeto do contratante correspondente
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContratanteResponse> find(@PathVariable Long id) {
        ContratanteResponse contratante = this.contratanteService.findById(id);
        return new ResponseEntity<>(contratante, HttpStatus.OK);
    }
}