package corallus.artConnect.artConnect.dto.response;

import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;

import java.time.LocalDateTime;

/** DTO de response das mensagem de um bate-papo.
 *
 * @param id Id da mensagem.
 * @param conteudo Texto da mensagem
 * @param dataEnvio Data de envio da mensagem.
 * @param autor Usuario que enviou a mensagem
 */
public record MensagemResponse(
        Long id,
        String conteudo,
        LocalDateTime dataEnvio,
        AutorResponse autor
) {}
