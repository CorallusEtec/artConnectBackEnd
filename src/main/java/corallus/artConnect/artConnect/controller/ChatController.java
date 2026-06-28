package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.ChatMessageRequest;
import corallus.artConnect.artConnect.dto.response.ChatMessageResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.ChatService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Tag(name = "Chat Controller", description = "Controller que gerencia as ações relacionadas à troca de mensagens via chat do sistema.")
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    // INJEÇÃO DE DEPENDÊNCIA
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send")
    public void receiveSendMessage(ChatMessageRequest request) {

        var response = this.chatService.save(request);
        //String recipientId = String.valueOf(request.recipientId());

        String canal = "/topic/chat." + Math.min(request.senderId(), request.recipientId()) + "-" + Math.max(request.senderId(), request.recipientId());
        messagingTemplate.convertAndSend(canal, response);

        //this.messagingTemplate.convertAndSendToUser(recipientId, "/queue/messages", response);
    }

    /** Retorna a lista de usuários que já tem conversa com o usuario autenticado.
     *
     * @param usuario Referência do usuário autenticado
     * @return Lista de usuários que já conversaram com o usuário autenticado.
     */
    @GetMapping("/contactsHistory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas.")
    })
    public ResponseEntity<List<UsuarioResponse>> contactsHistory(@AuthenticationPrincipal Usuario usuario) {
        System.out.println("Teste");
        System.out.println(usuario.getId());
        List<UsuarioResponse> history = this.chatService.history(usuario);
        return new ResponseEntity<>(history, HttpStatus.OK);

    }

    @GetMapping("/messageHistory/{recipientId}")
    public ResponseEntity<Page<ChatMessageResponse>> messageHistory(
            @PathVariable Long recipientId,
            @AuthenticationPrincipal Usuario usuario,
            @ParameterObject @PageableDefault(size = 12) Pageable pageable
    ) {
        Page<ChatMessageResponse> history = this.chatService.messageHistory(usuario, recipientId, pageable);
        return ResponseEntity.ok(history);
    }
}
