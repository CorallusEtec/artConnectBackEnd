package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.SendMessageRequest;
import corallus.artConnect.artConnect.entity.ChatMessage;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.MensagemRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final StatusService statusService;
    // INJEÇÃO DE DEPENDÊNCIA
    public MensagemService(MensagemRepository mensagemRepository, UsuarioRepository usuarioRepository, StatusService statusService) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.statusService = statusService;
    }

    public void save(SendMessageRequest request) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMensagem(request.message());
        chatMessage.setSender(this.usuarioRepository
                .findById(request.senderId())
                .orElseThrow(()->new UserNotFoundException("Remetente não existente")));

        chatMessage.setRecipient(this.usuarioRepository
                .findById(request.recipientId())
                .orElseThrow(()->new UserNotFoundException("Destinatário não existente")));
        chatMessage.setStatus(this.statusService.generateStatus());

        this.mensagemRepository.save(chatMessage);
    }


}
