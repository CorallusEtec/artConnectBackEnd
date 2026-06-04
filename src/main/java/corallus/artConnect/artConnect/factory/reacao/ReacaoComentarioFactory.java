package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ReacaoComentarioFactory implements ReacaoFactoryCreator{
    private final ComentarioRepository comentarioRepository;
    private final ReacaoFactory reacaoFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoComentarioFactory(
            ComentarioRepository comentarioRepository,
            ReacaoFactory reacaoFactory
    ){
        this.comentarioRepository = comentarioRepository;
        this.reacaoFactory = reacaoFactory;
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

        return this.reacaoFactory.compararReacao(reacaoAtual, reacao);
    }
}
