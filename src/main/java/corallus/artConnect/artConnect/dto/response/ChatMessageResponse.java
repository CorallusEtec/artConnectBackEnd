package corallus.artConnect.artConnect.dto.response;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;

import java.time.LocalDateTime;

public record ChatMessageResponse(
        UsuarioResponse recipient,
        UsuarioResponse sender,
        String mensagem,
        LocalDateTime dataEnvio
) {
}
