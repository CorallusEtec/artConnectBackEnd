package corallus.artConnect.artConnect.dto.request.comentario;

import jakarta.validation.constraints.NotEmpty;

public record ComentarioRequest(

        @NotEmpty(message = "O comentário não pode ser vazio")
        String mensagem,
        @NotEmpty(message = "Não foi possível comentar")
        Long idAutor,
        @NotEmpty(message = "Não foi possível comentar")
        Long idPublicacao
) {}
