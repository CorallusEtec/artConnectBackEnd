package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import corallus.artConnect.artConnect.repository.reacao.ReacaoRepository;
import org.springframework.stereotype.Component;


@Component
public class ReacaoComentarioFactory implements ReacaoFactoryCreator{
    private final ComentarioRepository comentarioRepository;
    private final ReacaoRepository reacaoRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoComentarioFactory(
            ComentarioRepository comentarioRepository,
            ReacaoRepository reacaoRepository
    ){
        this.comentarioRepository = comentarioRepository;
        this.reacaoRepository = reacaoRepository;
    }

    @Override
    public Reacao composeReacao(Reacao reacao, ReacaoRequest reacaoRequest) {
        // VERIFICA SE EXISTE E PEGA O COMENTARIO
        var comentario = this.comentarioRepository.findById(reacaoRequest.idRecurso())
                .orElseThrow(()->new ResourceNotFoundException("Comentário não encontrado"));
        // RELACIONA O COMENTÁRIO ENCONTRADO COM A REAÇÃO
        reacao.setComentario(comentario);

        // BUSCA A REAÇÃO JA EXISTENTE (OU NÃO)
        var reacaoAtual = comentario.getReacoes().stream()
                .filter(r->r.getUsuario().getId().equals(reacao.getUsuario().getId()))
                .findFirst();

        // SE TIVER UMA REAÇÃO
        if(reacaoAtual.isPresent()) {
            // A REAÇÃO ATUALIZADA VAI TER O MESMO ID DA ATUAL
            reacao.setId(reacaoAtual.get().getId());

            // SE TIVER E FOR A MESMA REAÇÃO
            if(reacao.getTipoReacao().getNomeTipo() == reacaoAtual.get().getTipoReacao().getNomeTipo()) {
                this.reacaoRepository.deleteById(reacao.getId());
                return null;
            }
        }
        // SE NÃO FOR A MESMA REAÇÃO OU SE NÃO EXISTIR, CRIA UMA NOVA REAÇÃO
        return this.reacaoRepository.save(reacao);
    }
}
