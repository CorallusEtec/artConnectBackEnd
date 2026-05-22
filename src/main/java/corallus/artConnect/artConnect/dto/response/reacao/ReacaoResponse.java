package corallus.artConnect.artConnect.dto.response.reacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

public record ReacaoResponse(
    Boolean empty,
    Long idPublicacao,
    Long idComentario,
    LocalDateTime dataReacao,
    TipoReacao tipoReacao,
    UsuarioResponse usuario
) {
    public static ReacaoResponse toDTO(Reacao entity) {
        ReacaoResponse dto = new ReacaoResponse(
        false,
        entity.getPublicacao()==null?null:entity.getPublicacao().getId(), 
        entity.getComentario()==null?null:entity.getComentario().getId(),
        entity.getDataReacao(),
        entity.getTipoReacao(),
        UsuarioResponse.toDTO(entity.getUsuario()));
          return dto;
    }
    
    public static ReacaoResponse emptyDto() {
        ReacaoResponse dto = new ReacaoResponse(true, null, null, null, null, null);
        return dto;
    }
}
