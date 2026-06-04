package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import corallus.artConnect.artConnect.repository.reacao.ReacaoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        reacao.setComentario(comentario);

        // BUSCA A REAÇÃO JA EXISTENTE (OU NÃO)
        var reacaoAtual = comentario.getReacoes().stream()
                .filter(r->r.getUsuario().getId().equals(reacao.getUsuario().getId()))
                .findFirst();

        if(ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao)==null) {

            return ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao);
        } else {
            return this.reacaoRepository.save(Objects.requireNonNull(ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao)));
        }
    }
}
