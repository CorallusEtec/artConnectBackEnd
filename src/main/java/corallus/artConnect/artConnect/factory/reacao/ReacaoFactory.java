package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.service.TipoReacaoService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ReacaoFactory implements ReacaoFactoryCreator {

    private final ReacaoPublicacaoFactory reacaoPublicacaoFactory;
    private final ReacaoComentarioFactory reacaoComentarioFactory;
    private final UsuarioRepository usuarioRepository;
    private final TipoReacaoService tipoReacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoFactory(
            ReacaoPublicacaoFactory reacaoPublicacaoFactory,
            ReacaoComentarioFactory reacaoComentarioFactory,
            UsuarioRepository usuarioRepository,
            TipoReacaoService tipoReacaoService
    ) {
        this.reacaoPublicacaoFactory = reacaoPublicacaoFactory;
        this.reacaoComentarioFactory = reacaoComentarioFactory;
        this.usuarioRepository = usuarioRepository;
        this.tipoReacaoService = tipoReacaoService;
    }

    /**
     * Cria uma nova reação ou altera uma reação que já existe, seja em publicação seja em reações.
     *
     * @param request DTO de reação
     * @return Retorna a reação instânciada de perssistida no banco
     */
    public Reacao createReacao(ReacaoRequest request) {
        Reacao reacao = this.composeReacao(new Reacao(), request);

        switch (request.tipoRecurso()) {
            case "COMENTARIO" -> {
                return this.reacaoComentarioFactory.composeReacao(reacao, request);
            } case "PUBLICACAO" -> {
                return this.reacaoPublicacaoFactory.composeReacao(reacao, request);
            } default -> throw new IllegalArgumentException("Tipo de recurso indisponível para reações");
        }
    }

    @Override
    public Reacao composeReacao(Reacao reacao, ReacaoRequest reacaoRequest) {
        reacao.setDataReacao(LocalDateTime.now());
        reacao.setUsuario(this.usuarioRepository.findById(reacaoRequest.idAutor()).get());
        reacao.setTipoReacao(this.tipoReacaoService.getTipoReacao(ETipoReacao.valueOf(reacaoRequest.nomeTipoReacao())));
        return reacao;
    }
}
