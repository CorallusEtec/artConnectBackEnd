package corallus.artConnect.artConnect.factory.reacao;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.entity.Reacao;

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
}
