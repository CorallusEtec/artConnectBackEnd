package corallus.artConnect.artConnect.dto.response.reacao;

import corallus.artConnect.artConnect.enumeration.ETipoReacao;

/**
 * Response que traz as reações do comentário, compõe a Response
 *
 * @param tipoReacao nome do tipo de reação
 * @param total total de reações dessa reação no comentario
 */
public record ReacaoComentarioResponse(
        ETipoReacao tipoReacao,
        Integer total
) {
}
