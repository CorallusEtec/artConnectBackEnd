package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.SendMessageRequest;
import corallus.artConnect.artConnect.entity.ChatMessage;
import corallus.artConnect.artConnect.service.MensagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Tag(name = "Chat Controller", description = "Controller que gerencia as ações relacionadas à troca de mensagens via chat do sistema.")
public class ChatController {
    private final MensagemService mensagemService;
    private final SimpMessagingTemplate messagingTemplate;

    // INJEÇÃO DE DEPENDÊNCIA
    public ChatController(MensagemService mensagemService, SimpMessagingTemplate messagingTemplate) {
        this.mensagemService = mensagemService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send")
    public void receiveSendMessage(SendMessageRequest request) {

        this.mensagemService.save(request);
        String recipientId = String.valueOf(request.recipientId());

        this.messagingTemplate.convertAndSendToUser(recipientId, "/queue/messages", request);
    }
}
