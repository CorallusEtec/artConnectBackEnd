package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.repository.arte.ArteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author SamuMeneDev
 */
@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final ArteRepository arteRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminService(UsuarioRepository usuarioRepository, ArteRepository arteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.arteRepository = arteRepository;
    }

    public RelatorioResponse getRelatorio() {

        return RelatorioResponse.builder()
                .artistasCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.ARTISTA))
                .contratantesCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.CONTRATANTE))
                .artes(this.arteRepository.arteRelatorio())
                .build();
    }
}
