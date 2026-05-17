package corallus.artConnect.artConnect.dto.comentario;

/**
 * DTO PARA POSTAR COMENTÁRIO. NÃO CONFUNDIR 'POST' COM AS POSTAGENS
 * ESSA DTO É PARA O MÉTODO POST (SALVAR COMENTÁRIO)
 * 
 */
public record ComentarioPostDTO(
    String mensagem,
    Long idAutor,
    Long idPublicacao
) {}
