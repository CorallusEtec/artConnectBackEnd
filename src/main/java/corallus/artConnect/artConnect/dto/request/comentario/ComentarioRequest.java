package corallus.artConnect.artConnect.dto.request.comentario;

public record ComentarioRequest(
    String mensagem,
    Long idAutor,
    Long idPublicacao
) {}
