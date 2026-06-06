package corallus.artConnect.artConnect.dto.response.comentario;

import java.time.LocalDateTime;
import java.util.Set;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.Reacao;

public record ComentarioResponse(
    Long id,
    Status statusComentario,
    LocalDateTime dataComentario,
    String mensagem,
    UsuarioResponse autor,
    Long idPublicacao,
    Set<Reacao> reacoes
) {}
