package corallus.artConnect.artConnect.dto.publicacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.UsuarioDTO;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import corallus.artConnect.artConnect.entity.publicacao.TipoReacao;

public record ReacaoDTO(
    Boolean empty,
    Long idPublicacao,
    Long idComentario,
    LocalDateTime dataReacao,
    TipoReacao tipoReacao,
    UsuarioDTO usuario
) {
    public static ReacaoDTO toDTO(Reacao entity) {
        ReacaoDTO dto = new ReacaoDTO(
        false,
        entity.getPublicacao()==null?null:entity.getPublicacao().getId(), 
        entity.getComentario()==null?null:entity.getComentario().getId(),
        entity.getDataReacao(),
        entity.getTipoReacao(),
        UsuarioDTO.toDTO(entity.getUsuario()));
          return dto;
    }
    
    public static ReacaoDTO emptyDto() {
        ReacaoDTO dto = new ReacaoDTO(true, null, null, null, null, null);
        return dto;
    }
}
