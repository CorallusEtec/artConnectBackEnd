package corallus.artConnect.artConnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MensagemRequest(
        @NotBlank( message = "A mensagem não pode ser vazia")
        String conteudo,
        @NotNull(message = "O id do destinatáio não pode estar vazio")
        Long destinoId
) {
}
