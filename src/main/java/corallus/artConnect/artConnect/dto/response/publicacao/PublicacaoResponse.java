package corallus.artConnect.artConnect.dto.response.publicacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public record PublicacaoResponse (

    Long id,
    String legenda,
    String urlMidia,
    LocalDateTime dataPublicacao,

    UsuarioResponse autor,
    Integer totalReacoes,
    Integer totalComentarios
) {

    public static PublicacaoResponse toDTO (Publicacao pub) {
        return new PublicacaoResponse(
            pub.getId(),
            pub.getLegenda(),
            pub.getUrlMidia(),
            pub.getDataPublicacao(),
            UsuarioResponse.toDTO(pub.getAutor()),

            pub.getReacoes() != null ? pub.getReacoes().size() : 0,
            pub.getComentarios() != null ? pub.getComentarios().size() : 0
        );
    }
}

