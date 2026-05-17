package corallus.artConnect.artConnect.dto.comentario;

import java.time.LocalDateTime;
import java.util.Set;

import corallus.artConnect.artConnect.dto.UsuarioDTO;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.publicacao.Comentario;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;

/**
 * DTO PARA RETORNAR OS DADOS DO COMENTÁRIO.
 * ESSA DTO É PARA O MÉTODO GET (BUSCAR COMENTÁRIO)
 * 
 */
public record ComentarioGetDTO(
    Long id,
    Status statusComentario,
    LocalDateTime dataComentario,
    String mensagem,
    UsuarioDTO autor,
    Long idPublicacao,
    Set<Reacao> reacoes
) {
   public static ComentarioGetDTO toDTO(Comentario entity) {
    ComentarioGetDTO dto = new ComentarioGetDTO(
        entity.getId(),
        entity.getStatusComentario(),
        entity.getDataComentario(),
        entity.getMensagem(),
        UsuarioDTO.toDTO(entity.getUsuario()),
        entity.getPublicacao().getId(),
        entity.getReacoes()
    );
    return dto;
   } 
}
