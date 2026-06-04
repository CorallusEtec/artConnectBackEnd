package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

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
    private final PasswordEncoder passwordEncoder;
    private final StatusService statusService;

    // INJEÇÃO DE DEPENDÊNCIA
    public UsuarioFactory(
            ArtistaFactory artistaFactory,
            ContratanteFactory contratanteFactory,
            AdminFactory adminFactory,
            PasswordEncoder passwordEncoder,
            StatusService statusService
    ){
        this.artistaFactory = artistaFactory;
        this.contratanteFactory = contratanteFactory;
        this.adminFactory = adminFactory;
        this.passwordEncoder = passwordEncoder;
        this.statusService = statusService;
    }

    /**
     * Factory que instancia e perssiste um novo usuario baseado no tipo de conta desejada.
     *
     * @param registerRequest DTO com os dados do cadastro
     * @return Retorna o objeto do tipo usuario da generalização escolida
     */
    public Usuario createUsuario(UserRegisterRequest registerRequest) {
        switch (registerRequest.tipoConta()) {
            case "ARTISTA" -> {
                var a = this.composeUsuario(new Artista(), registerRequest);
                return this.artistaFactory.composeUsuario(a, registerRequest);
            } case "CONTRATANTE" -> {
                var c = this.composeUsuario(new Contratante(), registerRequest);
                return this.contratanteFactory.composeUsuario(c, registerRequest);
            } case "ADMIN" -> {
                var admin = this.composeUsuario(new Admin(), registerRequest);
                return this.adminFactory.composeUsuario(admin, registerRequest);
            } default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UserRegisterRequest registerRequest) {
        usuario.setEmail(registerRequest.email());
        usuario.setId(null); // Garantir que criará um novo
        usuario.setNome(registerRequest.nome());
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setSenha(this.passwordEncoder.encode(registerRequest.senha()));

        usuario.setStatus(this.statusService.generateStatus());
        return usuario;
    }
}
