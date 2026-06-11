package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.request.arte.ArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.ArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.service.ArteService;

@RequestMapping("/arte")
@RestController
@Tag(name = "Arte Controller", description = "Operações e buscas sobre os tipos de arte.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ArteController {
    private final ArteService arteService;
    // INJEÇÃO DE DEPENDÊNCIA
    public ArteController(ArteService arteService) {
        this.arteService = arteService;
    }

    /**
     * Busca todos os tipos de artes cadastradas no sistema, com busca paginada.
     *
     * @param pageable Configurações da paginação que podem ser alteradas na requisição.
     * @return Retorna uma lista paginada com os tipos de arte do sistema.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<Arte>> findAll(
            @ParameterObject
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable
    ) {
        Page<Arte> lista = this.arteService.findAll(pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Busca uma arte pelo Id
     *
     * @param id Id da arte buscada.
     * @return O objeto com os dados da arte correnspondente.
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Arte não encontrada.")
    })
    public ResponseEntity<Arte> findById(
            @PathVariable Long id
    ) {
        Arte arte = this.arteService.findById(id);
        return new ResponseEntity<>(arte, HttpStatus.OK);
    }

    /**
     * Cadastra uma nova arte no sistema
     *
     * @param saveRequest Request com os dados cadastrais de uma nova arte.
     * @return Mensagem caso a arte tenha sido cadastrada com sucesso.
     */
    @PostMapping("/save")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arte criada."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "409", description = "Arte já cadastrada.")
    })
    public ResponseEntity<MessageResponse> save(
            @RequestBody @Valid
            ArteSaveRequest saveRequest
    ) {
        MessageResponse msg = this.arteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Altera o nome de um tipo de arte pelo Id.
     *
     * @param id Id da arte correspondente.
     * @param editRequest Request com os novos valores da arte.
     * @return Mensagem caso a arte tenha sido alterada com sucesso.
     */
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arte alterada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Arte não encontrada.")
    })
    public ResponseEntity<MessageResponse> edit(
            @PathVariable Long id,
            @RequestBody @Valid
            ArteEditRequest editRequest
    ) {
        MessageResponse msg = this.arteService.edit(id, editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Exclui um tipo de arte do sistema pelo Id.
     *
     * @param id ID da arte a ser deletada.
     * @return Mensagem caso a arte tenha sido excluida com sucesso.
     */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arte deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Arte não encontrada.")
    })
    public ResponseEntity<MessageResponse> delete(
            @PathVariable Long id
    ) {
        MessageResponse msg = this.arteService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
