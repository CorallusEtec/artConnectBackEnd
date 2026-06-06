package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.arte.ArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.ArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
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

public class ArteController {
    private final ArteService arteService;
    // INJEÇÃO DE DEPENDÊNCIA
    public ArteController(ArteService arteService) {
        this.arteService = arteService;
    }

    /**
     * Busca todas os tipos de arte cadastradas no sistema, com busca paginada.
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
     * Busca uma arte pelo ID
     *
     * @param id ID da arte buscada.
     * @return O objeto com os dados da arte correnspondente.
     */
    @GetMapping("/{id}")
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
    public ResponseEntity<MessageResponse> save(
            @RequestBody @Valid
            ArteSaveRequest saveRequest
    ) {
        MessageResponse msg = this.arteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Altera o nome de um tipo de arte pelo ID.
     *
     * @param id ID da arte correspondente.
     * @param editRequest Request com os novos valores da arte.
     * @return Mensagem caso a arte tenha sido alterada com sucesso.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> edit(
            @PathVariable Long id,
            @RequestBody @Valid
            ArteEditRequest editRequest
    ) {
        MessageResponse msg = this.arteService.edit(id, editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Exclui um tipo de arte do sistema pelo ID.
     *
     * @param id ID da arte a ser deletada.
     * @return Mensagem caso a arte tenha sido excluida com sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(
            @PathVariable Long id
    ) {
        MessageResponse msg = this.arteService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
