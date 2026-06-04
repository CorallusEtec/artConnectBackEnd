package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import java.util.Optional;

/**
 * Interface que define a assinatura para o pattern de Factory
 *
 * @author SamuMeneDev
 * @since 04/06/2026
 */
public interface ReacaoFactoryCreator {

    /**
     *
     *
     * @param reacao Reacao que representa a requisição de reação.
     * @param reacaoRequest DTO de reação para validar e desviar o fluxo de instância.
     * @return retorna a Reação instânciada e perssistida seja no comentario, seja na publicação.
     */
    Reacao composeReacao(Reacao reacao, ReacaoRequest reacaoRequest);


    /**
     * Metodo que faz as comparações entre a reação que está no banco de dados (ou não)
     * e a reação que será perssistida.
     *
     * @param reacaoAtual A reação que está no banco de dados
     * @param reacaoNova A reação que será perssistida
     * @return A reação preparada para ser salva no banco. Caso o tipo da reação seja o mesmo entre as duas, o retorno é null
     * @apiNote O parametro <i>reacaoAtual</i> deve estar com o usuario e o id do usuario configurado,
     * caso contrário a comparação não será feita corretamente
     */
    static Reacao compararReacao(Optional<Reacao> reacaoAtual, Reacao reacaoNova) {
        // SE TIVER UMA REAÇÃO
        if(reacaoAtual.isPresent()) {
            // A REAÇÃO ATUALIZADA VAI TER O MESMO ID DA ATUAL
            reacaoNova.setId(reacaoAtual.get().getId());

            // SE TIVER E FOR A MESMA REAÇÃO
            if(reacaoNova.getTipoReacao().getNomeTipo() == reacaoAtual.get().getTipoReacao().getNomeTipo()) {
                return null;
            }
        }
        // SE NÃO FOR A MESMA REAÇÃO OU SE NÃO EXISTIR, CRIA UMA NOVA REAÇÃO
        return reacaoNova;
    }
}
