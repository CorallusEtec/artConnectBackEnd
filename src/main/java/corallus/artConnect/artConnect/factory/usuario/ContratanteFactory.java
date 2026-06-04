package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.service.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ContratanteFactory implements UsuarioFactoryCreator {

    private final PasswordEncoder passwordEncoder;
    private final StatusService statusService;
    private final ContratanteRepository contratanteRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteFactory(
                    PasswordEncoder passwordEncoder,
                    StatusService statusService,
                    ContratanteRepository contratanteRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.statusService = statusService;
        this.contratanteRepository = contratanteRepository;
    }

    /**
     * Implementação que instância e perssiste um novo {@link Usuario} com a especialização de {@link Contratante}
     *
     * @param registerRequest DTO de cadastro
     * @return retorna um contratante já instânciado.
     */

    @Override
    public Usuario createUsuario(UserRegisterRequest registerRequest) {

        Contratante contratante = new Contratante();
        contratante.setTipoConta(ETipoConta.valueOf(registerRequest.tipoConta()));

        String senha = this.passwordEncoder.encode(registerRequest.senha());

        contratante.setNome(registerRequest.nome());
        contratante.setSenha(senha);
        contratante.setEmail(registerRequest.email());
        contratante.setDataCriacao(LocalDateTime.now());

        contratante.setStatus(this.statusService.generateStatus());
        return this.contratanteRepository.save(contratante);
    }
}
