package corallus.artConnect.artConnect.dto.request.comentario;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequest(

        @NotBlank(message = "O comentário não pode ser vazio")
        String mensagem,
        @NotBlank(message = "Não foi possível comentar")
        Long idAutor,
        @NotBlank(message = "Não foi possível comentar")
        Long idPublicacao
) {}
