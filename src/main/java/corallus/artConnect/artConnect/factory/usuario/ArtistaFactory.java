package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import corallus.artConnect.artConnect.service.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ArtistaFactory implements UsuarioFactoryCreator {

    private final PasswordEncoder passwordEncoder;
    private final StatusService statusService;
    private final ArtistaRepository artistaRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ArtistaFactory(
            PasswordEncoder passwordEncoder,
            StatusService statusService,
            ArtistaRepository artistaRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.statusService = statusService;
        this.artistaRepository = artistaRepository;
    }

    @Override
    public Usuario createUsuario(UserRegisterRequest registerRequest) {
        Artista artista = new Artista();
        artista.setTipoConta(ETipoConta.ARTISTA);
        String senha = this.passwordEncoder.encode(registerRequest.senha());

        artista.setNome(registerRequest.nome());
        artista.setEmail(registerRequest.email());
        artista.setSenha(senha);
        artista.setDataCriacao(LocalDateTime.now());
        artista.setStatus(this.statusService.generateStatus());

        return this.artistaRepository.save(artista);
    }
}
