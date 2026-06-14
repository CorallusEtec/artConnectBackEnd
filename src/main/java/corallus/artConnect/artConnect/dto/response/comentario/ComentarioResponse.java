package corallus.artConnect.artConnect.dto.response.comentario;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoDetailsResponse;
import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import lombok.Builder;

@Builder
public record ComentarioResponse(
    Long id,
    Status status,
    LocalDateTime dataComentario,
    String mensagem,
    AutorResponse usuario,
    Long likes,
    ETipoReacao reacaoUsuario,
    PublicacaoDetailsResponse publicacao
) {}
