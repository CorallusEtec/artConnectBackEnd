package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.Reacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ReacaoFactory {

    private final ReacaoPublicacaoFactory reacaoPublicacaoFactory;
    private final ReacaoComentarioFactory reacaoComentarioFactory;
    private final UsuarioRepository usuarioRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoFactory(
            ReacaoPublicacaoFactory reacaoPublicacaoFactory,
            ReacaoComentarioFactory reacaoComentarioFactory,
            UsuarioRepository usuarioRepository
    ) {
        this.reacaoPublicacaoFactory = reacaoPublicacaoFactory;
        this.reacaoComentarioFactory = reacaoComentarioFactory;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria uma nova reação ou altera uma reação que já existe, seja em publicação seja em reações.
     *
     * @param request DTO de reação
     * @return Retorna a reação instânciada de perssistida no banco
     */
    public Reacao createReacao(Long idAutor, ReacaoRequest request) {
        // INICIALIZA UMA NOVA REAÇÃO
        Reacao reacao = this.composeReacao(idAutor, new Reacao(), request);
        switch (request.tipoRecurso()) {
            case "COMENTARIO" -> {
                // RETORNA A REAÇÃO JÁ CRIADA
                return this.reacaoComentarioFactory.composeReacao(reacao, request);
            } case "PUBLICACAO" -> {
                // RETORNA A REAÇÃO JÁ CRIADA
                return this.reacaoPublicacaoFactory.composeReacao(reacao, request);
            } default -> throw new IllegalArgumentException("Tipo de recurso indisponível para reações");
        }
    }

    // CONFIGURAÇÕES QUE TODA REAÇÃO TEM
    public Reacao composeReacao(Long idAutor, Reacao reacao, ReacaoRequest reacaoRequest) {
        reacao.setDataReacao(LocalDateTime.now());
        reacao.setUsuario(this.usuarioRepository.findById(idAutor).get());
        reacao.setTipoReacao(ETipoReacao.valueOf(reacaoRequest.nomeTipoReacao()));
        return reacao;
    }
}
