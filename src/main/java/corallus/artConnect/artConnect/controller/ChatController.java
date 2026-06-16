package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.MensagemRequest;
import corallus.artConnect.artConnect.dto.response.MensagemResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.MensagemService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat Controller", description = "Controller que gerencia as ações relacionadas à troca de mensagens via chat do sistema.")
public class ChatController {
    private final MensagemService mensagemService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ChatController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    /** Busca histórico das mensagens de um chat pelo Id, com paginação das mensagens
     *
     * @param roomId Id do chat buscado.
     * @param pageable Configurações de paginação.
     * @return Lista Paginada com as mensagens
     */
    @GetMapping("/history/{roomId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<Page<MensagemResponse>> getHistory(
            @PathVariable Long roomId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        Page<MensagemResponse> historico = this.mensagemService.getHistory(roomId, pageable);
        return new ResponseEntity<>(historico, HttpStatus.OK);
    }

    /** Envia mensagem para a sala indicada na request.
     *
     * @param request Dados para direcionar o envio da mensagem.
     * @param usuario Referência do usuario autenticado
     * @return Mensagem caso a mensagem tenha sido enviada.
     */
    @PostMapping("/sendMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Não Autenticado"),
            @ApiResponse(responseCode = "404", description = "Sala (chat) não encontrada(o)")

    })
    public ResponseEntity<MessageApiResponse> sendMessage(
            @RequestBody @Valid MensagemRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        MessageApiResponse msg = this.mensagemService.sendMessage(request, usuario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
