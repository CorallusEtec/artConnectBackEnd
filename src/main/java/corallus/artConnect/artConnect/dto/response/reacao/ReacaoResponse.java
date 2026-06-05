package corallus.artConnect.artConnect.dto.response.reacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioPublicacaoResponse;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

/** <h3>RecaoResponse</h3>
 * <p>
 *     DTO de response da entidade Reacao
 * </p>
 *
 * @param idPublicacao Se for em uma publicação, o id da publicação relativa
 * @param idComentario Se for em um comentário, o id da comentário relativa
 * @param dataReacao Data de quando foi curtido
 * @param tipoReacao O tipo dessa reação (LIKE, DESLIKE etc)
 */
public record ReacaoResponse(
    Long idPublicacao,
    UsuarioPublicacaoResponse usuario,
    Long idComentario,
    LocalDateTime dataReacao,
    TipoReacao tipoReacao
) {}
