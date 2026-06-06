package corallus.artConnect.artConnect.dto.response.publicacao;

import corallus.artConnect.artConnect.dto.response.reacao.ReacaoPublicacaoResponse;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import lombok.Builder;

import java.util.List;

/**
 * Response que engloba outras responses relacionadas à publicação.
 *
 * @param publicacao Detalhes específicos da publicacao.
 * @param reacoes Dados das reações da publicação.
 * @param reacaoUsuario Reação que o usuário autenticado fez nessa publicação.
 * Caso não tenha feito nenhuma, o valor é null.
 * @param totalComentarios Número total de comentários da publicação.
 */

@Builder
public record PublicacaoResponse(
        PublicacaoDetailsResponse publicacao,
        List<ReacaoPublicacaoResponse> reacoes,
        ETipoReacao reacaoUsuario,
        Integer totalComentarios
) {
}
