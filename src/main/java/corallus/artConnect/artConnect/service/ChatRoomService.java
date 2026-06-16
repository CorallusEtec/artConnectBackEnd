package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.entity.ChatRoom;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.ChatRoomRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UsuarioRepository usuarioRepository;
    // INJEÇÃO DE DEPENDÊNCIA
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UsuarioRepository usuarioRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ChatRoom findById(Long chatId) {
        return this.chatRoomRepository.findById(chatId)
                .orElseThrow(()-> new ResourceNotFoundException("Sala não encontrada"));

    }

    public ChatRoom getOrCreateRoom(Long usuarioId1, Long usuarioId2) {
        var usuario1 = this.usuarioRepository.findById(usuarioId1).orElseThrow(UserNotFoundException::new);
        var usuario2 = this.usuarioRepository.findById(usuarioId2).orElseThrow(UserNotFoundException::new);
        return this.chatRoomRepository.findByUsers(usuario1, usuario2)

                .orElseGet(()->{
                    ChatRoom room = new ChatRoom(usuario1, usuario2);
                    return this.chatRoomRepository.save(room);
                });
    }
}
