package corallus.artConnect.artConnect.dto.response.comentario;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoDetailsResponse;
import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;

public record ComentarioResponse(
    Long id,
    Status status,
    LocalDateTime dataComentario,
    String mensagem,
    AutorResponse usuario,
    PublicacaoDetailsResponse publicacao
) {}
