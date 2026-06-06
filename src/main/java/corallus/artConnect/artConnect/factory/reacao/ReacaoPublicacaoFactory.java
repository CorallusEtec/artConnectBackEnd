package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.reacao.ReacaoRepository;
import org.springframework.stereotype.Component;

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

        // RELACIONA A PUBLICAÇÃO ENCONTRADA COM A REAÇÃO
        reacao.setPublicacao(publi);

        // BUSCA A REAÇÃO JA EXISTENTE (OU NÃO)
        var reacaoAtual = publi.getReacoes().stream()
                .filter(r->r.getUsuario().getId().equals(reacao.getUsuario().getId()))
                .findFirst();

        // SE TIVER UMA REAÇÃO
        if(reacaoAtual.isPresent()) {
            // A REAÇÃO ATUALIZADA VAI TER O MESMO ID DA ATUAL
            reacao.setId(reacaoAtual.get().getId());
            reacao.setPublicacao(reacaoAtual.get().getPublicacao());

            // SE TIVER E FOR A MESMA REAÇÃO
            if(reacao.getTipoReacao().getNomeTipo() == reacaoAtual.get().getTipoReacao().getNomeTipo()) {
                this.reacaoRepository.deleteById(reacao.getId());
                return null;
            }
        }
        // SE NÃO FOR A MESMA REAÇÃO OU SE NÃO EXISTIR, CRIA UMA NOVA REAÇÃO
        reacao.setAtivo(true);

        return this.reacaoRepository.save(reacao);
    }
}
