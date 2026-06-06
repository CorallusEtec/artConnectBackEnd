package corallus.artConnect.artConnect.dto.response.reacao;

import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import lombok.Builder;

/**
 * Response que traz as reações da publicacao, compõe a Response
 *
 * @param tipoReacao nome do tipo de reação
 * @param total total de reações dessa reação na publicacao
 */

@Builder
public record ReacaoPublicacaoResponse(
        ETipoReacao tipoReacao,
        Integer total
) {}
