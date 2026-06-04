package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import org.springframework.stereotype.Component;

@Component
public class ReacaoPublicacaoFactory implements ReacaoFactoryCreator{
    private final PublicacaoRepository publicacaoRepository;
    private final ReacaoFactory reacaoFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoPublicacaoFactory(
            PublicacaoRepository publicacaoRepository,
            ReacaoFactory reacaoFactory
    ){
        this.publicacaoRepository = publicacaoRepository;
        this.reacaoFactory = reacaoFactory;
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

        return this.reacaoFactory.compararReacao(reacaoAtual, reacao);
    }


}
