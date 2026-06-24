package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.SeguidaRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class SeguidaService {
    private final SeguidaRepository seguidaRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguidaService(SeguidaRepository seguidaRepository, UsuarioRepository usuarioRepository) {
        this.seguidaRepository = seguidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public MessageApiResponse seguir(Long seguidorId, Long seguidoId) {
        if (seguidorId.equals(seguidoId)) {
            throw new IllegalArgumentException("Usuário não pode seguir a si mesmo.");
        }

        Usuario seguidor = usuarioRepository.findById(seguidorId).orElseThrow(UserNotFoundException::new);
        Usuario seguido  = usuarioRepository.findById(seguidoId).orElseThrow(UserNotFoundException::new);

        if (seguidaRepository.existsBySeguidorAndSeguido(seguidor, seguido)) {
            seguidaRepository.findBySeguidorAndSeguido(seguidor, seguido)
                    .ifPresent(seguidaRepository::delete);
            return new MessageApiResponse("Deixou de seguir");
        }

        Seguida seguida = new Seguida();
        seguida.setSeguidor(seguidor);
        seguida.setSeguido(seguido);
        seguidaRepository.save(seguida);
        return new MessageApiResponse("Seguindo");
    }
}