package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ArtistaFactory implements UsuarioFactoryCreator {

    private final ArtistaRepository artistaRepository;
    // INJEÇÃO DE DEPENDÊNCIA
    public ArtistaFactory(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UsuarioRegisterRequest registerRequest) {
        Artista artista = (Artista) usuario;

        artista.setTipoConta(ETipoConta.ARTISTA);
        if(!Objects.isNull(registerRequest.principal().details())) {
            artista.setArte(registerRequest.principal().details().arte());
            artista.setGenerosArte(registerRequest.principal().details().generosArte());
        }

        return this.artistaRepository.save(artista);
    }
}
