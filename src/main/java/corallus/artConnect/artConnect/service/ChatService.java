package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.ChatMessageRequest;
import corallus.artConnect.artConnect.dto.response.ChatMessageResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.ChatMessage;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.mapper.ChatMessageMapper;
import corallus.artConnect.artConnect.mapper.usuario.UsuarioMapper;
import corallus.artConnect.artConnect.repository.MensagemRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
public class ChatService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final StatusService statusService;
    private final UsuarioMapper usuarioMapper;
    private  final ChatMessageMapper chatMessageMapper;
    // INJEÇÃO DE DEPENDÊNCIA
    public ChatService(MensagemRepository mensagemRepository, UsuarioRepository usuarioRepository, StatusService statusService, UsuarioMapper usuarioMapper, ChatMessageMapper chatMessageMapper) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.statusService = statusService;
        this.usuarioMapper = usuarioMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Transactional
    public ChatMessageResponse save(ChatMessageRequest request) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMensagem(request.mensagem());
        chatMessage.setSender(this.usuarioRepository
                .findById(request.senderId())
                .orElseThrow(()->new UserNotFoundException("Remetente não existente")));

        chatMessage.setRecipient(this.usuarioRepository
                .findById(request.recipientId())
                .orElseThrow(()->new UserNotFoundException("Destinatário não existente")));
        chatMessage.setStatus(this.statusService.generateStatus());

        return this.chatMessageMapper.toDTO(this.mensagemRepository.save(chatMessage));
    }

    public List<UsuarioResponse> history(Usuario usuario) {
        if(Objects.isNull(usuario)) {
            throw new NotAuthorizedException();
        }

        List<ChatMessage> ultimasMensagens = this.mensagemRepository.findLastMessagesPerConversation(usuario.getId());

    return ultimasMensagens.stream()
            .map(msg-> msg.getSender().getId().equals(usuario.getId()) ?
                    msg.getRecipient() : msg.getSender())
            .map(this.usuarioMapper::toDTO)
            .toList();
    }

    public Page<ChatMessageResponse> messageHistory(Usuario usuario, Long rectipientId, Pageable pageable) {
        if(Objects.isNull(usuario)) {
            throw new NotAuthorizedException();
        }
        if(!this.usuarioRepository.existsById(rectipientId)) {
            throw new UserNotFoundException("Contato requisitado não existente");
        }

        return this.mensagemRepository.findAllHistory(usuario.getId(), rectipientId, pageable)
            .map(this.chatMessageMapper::toDTO);
    }

}
