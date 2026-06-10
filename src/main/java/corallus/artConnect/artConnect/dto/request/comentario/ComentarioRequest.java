package corallus.artConnect.artConnect.dto.request.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioRequest(
        @NotBlank(message = "O comentário não pode ser vazio")
        String mensagem,

        @NotNull(message = "Não foi possível comentar")
        Long idPublicacao
) {}
