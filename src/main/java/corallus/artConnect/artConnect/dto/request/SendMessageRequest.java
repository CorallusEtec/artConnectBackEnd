package corallus.artConnect.artConnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendMessageRequest(
        @NotBlank(message = "A mensagem não pode estar vazio")
        String message,
        @NotNull(message = "A mensagem está sem remetente")
        Long senderId,
        @NotNull(message = "A mensagem está sem destinatário")
        Long recipientId
) {}
