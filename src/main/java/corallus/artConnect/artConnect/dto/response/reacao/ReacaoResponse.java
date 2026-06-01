package corallus.artConnect.artConnect.dto.response.reacao;

import java.time.LocalDateTime;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

/** <h3>RecaoResponse</h3>
 * <p>
 *     DTO de response da entidade Reacao
 * </p>
 *
 * @param empty Se a reação está assinalada pelo usuário
 * @param idPublicacao Se for em uma publicação, o id da publicação relativa
 * @param idComentario Se for em um comentário, o id da comentário relativa
 * @param dataReacao Data de quando foi curtido
 * @param tipoReacao O tipo dessa reação (LIKE, DESLIKE etc)
 * @param usuario Usuário autor dessa reação
 */
public record ReacaoResponse(
    Boolean empty,
    Long idPublicacao,
    Long idComentario,
    LocalDateTime dataReacao,
    TipoReacao tipoReacao,
    UsuarioResponse usuario
) {
    public static ReacaoResponse emptyDto() {
        ReacaoResponse dto = new ReacaoResponse(true, null, null, null, null, null);
        return dto;
    }
}
