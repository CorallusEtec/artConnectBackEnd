package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioAdminEditRequest;
import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.arte.ArteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.status.StatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *
 * @author SamuMeneDev
 */
@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final ArteRepository arteRepository;
    private final StatusRepository statusRepository;
    // INJEÇÃO DE DEPENDÊNCIA
    public AdminService(UsuarioRepository usuarioRepository, ArteRepository arteRepository, StatusRepository statusRepository) {
        this.usuarioRepository = usuarioRepository;
        this.arteRepository = arteRepository;
        this.statusRepository = statusRepository;
    }

    public RelatorioResponse getRelatorio() {

        return RelatorioResponse.builder()
                .artistasCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.ARTISTA))
                .contratantesCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.CONTRATANTE))
                .artes(this.arteRepository.arteRelatorio())
                .build();
    }

    public MessageApiResponse editUsuario(Long id, UsuarioAdminEditRequest editRequest) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);


        usuario.setNome(editRequest.nome());
        usuario.setNome(editRequest.email());
        editRequest.status().setDataModificacao(LocalDateTime.now());
        usuario.setStatus(this.statusRepository.save(editRequest.status()));

        return new MessageApiResponse("Usuario alterado com sucesso");
    }
}
