package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.MensagemRequest;
import corallus.artConnect.artConnect.dto.response.MensagemResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.ChatRoom;
import corallus.artConnect.artConnect.entity.Mensagem;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.mapper.MensagemMapper;
import corallus.artConnect.artConnect.repository.MensagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final MensagemMapper mensagemMapper;
    private final StatusService statusService;
    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate messagingTemplate;
    // INJEÇÃO DE DEPENDÊNCIA
    public MensagemService(
            MensagemRepository mensagemRepository,
            MensagemMapper mensagemMapper,
            StatusService statusService,
            ChatRoomService chatRoomService,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.mensagemRepository = mensagemRepository;
        this.mensagemMapper = mensagemMapper;
        this.statusService = statusService;
        this.chatRoomService = chatRoomService;
        this.messagingTemplate = messagingTemplate;
    }

    public Page<MensagemResponse> getHistory(Long roomId, Pageable pageable) {
        Page<Mensagem> pagina = this.mensagemRepository.findAllByChatRoom_Id(roomId, pageable);

        return pagina.map(this.mensagemMapper::toDTO);
    }

    public MessageApiResponse sendMessage(MensagemRequest request, Usuario usuario) {
        ChatRoom room = this.chatRoomService.getOrCreateRoom(usuario.getId(), request.destinoId());

        // Mensagem criada
        Mensagem msg = new Mensagem();
        msg.setAutor(usuario);
        msg.setConteudo(request.conteudo());
        msg.setStatus(this.statusService.generateStatus());
        msg.setChatRoom(room);

        this.mensagemRepository.save(msg);
        // /topic/messages"+msg.getChatRoom().getId()
        this.messagingTemplate.convertAndSend("/topic/geral", this.mensagemMapper.toDTO(msg));


        return new MessageApiResponse("Mensagem enviada");
    }
}
