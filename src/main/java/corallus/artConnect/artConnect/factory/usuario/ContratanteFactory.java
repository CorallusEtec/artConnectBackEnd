package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ContratanteFactory implements UsuarioFactoryCreator {

    private final ContratanteRepository contratanteRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteFactory(ContratanteRepository contratanteRepository) {
        this.contratanteRepository = contratanteRepository;
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UsuarioRegisterPrincipalRequest principal, MultipartFile fotoPerfil) {
        usuario.setTipoConta(ETipoConta.CONTRATANTE);
        return this.contratanteRepository.save((Contratante) usuario);
    }
}
