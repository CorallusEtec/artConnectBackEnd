package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import org.springframework.stereotype.Component;

@Component
public class ContratanteFactory implements UsuarioFactoryCreator {

    private final ContratanteRepository contratanteRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteFactory(ContratanteRepository contratanteRepository) {
        this.contratanteRepository = contratanteRepository;
    }

    /**
     * Implementação que instância e perssiste um novo {@link Usuario} com a especialização de {@link Contratante}
     *
     * @param registerRequest DTO de cadastro
     * @return retorna um contratante já instânciado.
     */

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UserRegisterRequest registerRequest) {
        usuario.setTipoConta(ETipoConta.CONTRATANTE);
        return this.contratanteRepository.save((Contratante) usuario);
    }
}
