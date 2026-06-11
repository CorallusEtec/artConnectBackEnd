package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import org.springframework.stereotype.Component;

@Component
public class ArtistaFactory implements UsuarioFactoryCreator {

    private final ArtistaRepository artistaRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ArtistaFactory(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UserRegisterRequest registerRequest) {
        usuario.setTipoConta(ETipoConta.ARTISTA);
        return this.artistaRepository.save((Artista) usuario);
    }
}
