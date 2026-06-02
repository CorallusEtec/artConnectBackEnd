package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.stereotype.Component;

/**
 * <h2>UsuarioFactory</h2>
 * Classe que usa o factory method para instânciar usuarios
 * @author SamuMeneDev
 * @implNote Essa classe usa o padrão <i>Factory</i>
 */
@Component
public class UsuarioFactory implements UsuarioFactoryCreator {

    private final ArtistaFactory artistaFactory;
    private final ContratanteFactory contratanteFactory;
    private final AdminFactory adminFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public UsuarioFactory(
            ArtistaFactory artistaFactory,
            ContratanteFactory contratanteFactory,
            AdminFactory adminFactory
    ){
        this.artistaFactory = artistaFactory;
        this.contratanteFactory = contratanteFactory;
        this.adminFactory = adminFactory;
    }

    @Override
    public Usuario createUsuario(UserRegisterRequest registerRequest) {
        switch (registerRequest.tipoConta()) {
            case "ARTISTA" -> {
                return this.artistaFactory.createUsuario(registerRequest);
            } case "CONTRATANTE" -> {
                return this.contratanteFactory.createUsuario(registerRequest);
            } case "ADMIN" -> {
              return this.adminFactory.createUsuario(registerRequest);
            } default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }
    }
}
