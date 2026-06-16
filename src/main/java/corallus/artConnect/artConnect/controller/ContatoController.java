package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.request.contato.ContatoEditRequest;
import corallus.artConnect.artConnect.dto.request.contato.ContatoSaveRequest;
import corallus.artConnect.artConnect.service.ContatoService;

@RestController
@RequestMapping("/contato")
@Tag(name = "Contato Controller", description = "Ações relacionada a contato dos usuarios.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ContatoController {

    private final ContatoService contatoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * Salva um contato do usuário autenticado no sistema.
     *
     * @param usuario Referência do usuário autenticado.
     * @param contato Request com os dados do contato a ser adicionado.
     * @return Mensagem caso o contato tenha sido adicionado com sucesso.
     */
    @PostMapping("/save")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contato salvo com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado"),
            @ApiResponse(responseCode = "404", description = "Tipo de contato não encontrada.")
    })
    public ResponseEntity<MessageApiResponse> save(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody @Valid ContatoSaveRequest contato
    ) {
        MessageApiResponse msg = this.contatoService.save(usuario, contato);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /**
     * Exclui o contato do usuário autenticado pelo Id.
     *
     * @param usuario Referência do usuário autenticado.
     * @param idContato Id do contato que será excluido.
     * @return Mensagem caso o contato tenha sido removido com sucesso.
     */
    @DeleteMapping("/{idContato}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato excluido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrada.")
    })
    public ResponseEntity<MessageApiResponse> delete(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long idContato
    ) {
        MessageApiResponse msg = this.contatoService.delete(usuario, idContato);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Altera o valor de um contato cadastrado no sistema pelo Id.
     *
     * @param idContato Id do contato que será editado.
     * @param editRequest Request com os dados que serão sobrepostos.
     * @return Mensagem caso o contato tenha sido editado com sucesso.
     */
    @PutMapping("/{idContato}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrada.")
    })
    public ResponseEntity<MessageApiResponse> edit(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long idContato,
            @RequestBody @Valid ContatoEditRequest editRequest
    ) {
        MessageApiResponse msg = this.contatoService.edit(usuario, idContato, editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
