package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.publicacao.PublicacaoAdminPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioAdminEditRequest;
import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.Denuncia;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.DenunciaRepository;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.arte.ArteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author SamuMeneDev
 */
@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final ArteRepository arteRepository;
    private final PublicacaoRepository publicacaoRepository;
    private final StatusService statusService;
    private final DenunciaRepository denunciaRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminService(UsuarioRepository usuarioRepository, ArteRepository arteRepository, PublicacaoRepository publicacaoRepository, StatusService statusService, DenunciaRepository denunciaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.arteRepository = arteRepository;
        this.publicacaoRepository = publicacaoRepository;
        this.statusService = statusService;
        this.denunciaRepository = denunciaRepository;
    }

    public RelatorioResponse getRelatorio() {

        return RelatorioResponse.builder()
                .artistasCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.ARTISTA))
                .contratantesCadastrados(this.usuarioRepository.countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus.ATIVO, ETipoConta.CONTRATANTE))
                .artes(this.arteRepository.arteRelatorio())
                .publicacaoSemana(this.publicacaoRepository.relatorioPublicacoes(LocalDate.now().minusDays(7).atTime(0,0,0), LocalDateTime.now()))
                .publicacoesRealizadas(this.publicacaoRepository.countAllByStatusPublicacao_TipoStatus(ETipoStatus.ATIVO))
                .build();
    }

    public MessageApiResponse editUsuario(Long id, UsuarioAdminEditRequest editRequest) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);


        usuario.setNome(editRequest.nome());
        usuario.setEmail(editRequest.email());
        editRequest.status().setDataModificacao(LocalDateTime.now());
        usuario.setStatus(this.statusService.modifyStatus(editRequest.status().getId(), editRequest.status()));

        return new MessageApiResponse("Usuario alterado com sucesso");
    }

    public MessageApiResponse alterStatusPublicacaoById(Long idPublicacao, PublicacaoAdminPatchStatusRequest request) {

        Publicacao publicacao = this.publicacaoRepository.findById(idPublicacao)
                .orElseThrow(ResourceNotFoundException::new);

        Status status = publicacao.getStatusPublicacao();
        status.setTipoStatus(ETipoStatus.valueOf(request.tipoStatus()));
        publicacao.setStatusPublicacao(this.statusService.modifyStatus(status.getId(), status));
        this.publicacaoRepository.save(publicacao);
        return new MessageApiResponse("Publicação excluida com sucesso");
    }

    public MessageApiResponse alterStatusDenunciaById(Long idDenuncia, DenunciaPatchStatusRequest request) {

        Denuncia denuncia = this.denunciaRepository.findById(idDenuncia)
                .orElseThrow(ResourceNotFoundException::new);

        Status status = denuncia.getStatus();
        status.setTipoStatus(ETipoStatus.valueOf(request.tipoStatus()));
        denuncia.setStatus(this.statusService.modifyStatus(status.getId(), status));
        this.denunciaRepository.save(denuncia);
        return new MessageApiResponse("Status alterado");
    }


}
