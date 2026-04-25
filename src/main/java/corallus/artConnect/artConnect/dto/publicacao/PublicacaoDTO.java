package corallus.artConnect.artConnect.dto.publicacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.UsuarioDTO;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public record PublicacaoDTO (

    Long id,
    String legenda,
    String urlMidia,
    LocalDateTime dataPublicacao,

    UsuarioDTO autor,
    int totalReacoes,
    int totalComentarios
) {

    public static PublicacaoDTO toDTO (Publicacao pub) {
        return new PublicacaoDTO(
            pub.getId(),
            pub.getLegenda(),
            pub.getUrlMidia(),
            pub.getDataPublicacao(),
            UsuarioDTO.toDTO(pub.getAutor()),

            pub.getReacoes() != null ? pub.getReacoes().size() : 0,
            pub.getComentarios() != null ? pub.getComentarios().size() : 0
        );
    }
}

