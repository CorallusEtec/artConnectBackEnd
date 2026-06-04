package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.reacao.ReacaoRepository;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class ReacaoPublicacaoFactory implements ReacaoFactoryCreator{
    private final PublicacaoRepository publicacaoRepository;
    private final ReacaoRepository reacaoRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoPublicacaoFactory(
            PublicacaoRepository publicacaoRepository,
            ReacaoRepository reacaoRepository
    ){
        this.publicacaoRepository = publicacaoRepository;
        this.reacaoRepository = reacaoRepository;
    }

    @Override
    public Reacao composeReacao(Reacao reacao, ReacaoRequest reacaoRequest) {
        // VERIFICA SE EXISTE E PEGA A PUBLICACAO
        var publi = this.publicacaoRepository.findById(reacaoRequest.idRecurso())
                .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));
        reacao.setPublicacao(publi);

        // BUSCA A REAÇÃO JA EXISTENTE (OU NÃO)
        var reacaoAtual = publi.getReacoes().stream()
                .filter(r->r.getUsuario().getId().equals(reacao.getUsuario().getId()))
                .findFirst();

        if(ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao)==null) {
            return ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao);
        } else {
            return this.reacaoRepository.save(Objects.requireNonNull(ReacaoFactoryCreator.compararReacao(reacaoAtual, reacao)));
        }
    }
}
