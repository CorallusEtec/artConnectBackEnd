package corallus.artConnect.artConnect.dto.response.publicacao;

import java.time.LocalDateTime;
import java.util.List;


import corallus.artConnect.artConnect.dto.response.reacao.ReacaoDetailsResponse;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;


public record PublicacaoResponse (

    Long id,
    String legenda,
    String urlMidia,
    LocalDateTime dataPublicacao,

    UsuarioResponse autor,
    Integer totalReacoes,
    Integer totalComentarios,

    List<ReacaoDetailsResponse> reacoes
) {

    // BUILDER

    public static class builder {
        private Long id;
        private String legenda;
        private String urlMidia;
        private LocalDateTime dataPublicacao;
        private UsuarioResponse autor;
        private Integer totalReacoes;
        private Integer totalComentarios;
        private List<ReacaoDetailsResponse> reacoes;


        public builder id(Long id) {
            this.id = id;
            return this;
        }


        public builder legenda(String legenda) {
            this.legenda = legenda;
            return this;
        }

        public builder urlMidia(String urlMidia) {
            this.urlMidia = urlMidia;
            return this;
        }

        public builder dataPublicacao(LocalDateTime dataPublicacao) {
            this.dataPublicacao = dataPublicacao;
            return this;
        }

        public builder autor(UsuarioResponse autor) {
            this.autor = autor;
            return this;
        }

        public builder   totalReacoes(Integer totalReacoes) {
            this.totalReacoes = totalReacoes;
            return this;
        }

        public builder totalComentarios(Integer totalComentarios) {
            this.totalComentarios = totalComentarios;
            return this;
        }
        public builder reacoes(List<ReacaoDetailsResponse> reacoes) {
            this.reacoes = reacoes;
            return this;
        }
        public PublicacaoResponse build() {
            return new PublicacaoResponse(id, legenda, urlMidia, dataPublicacao, autor, totalReacoes, totalComentarios, reacoes);
        }
    }
    /*
    public static PublicacaoResponse toDTO (Publicacao pub) {
        
        return new PublicacaoResponse.builder()
            .id(pub.getId())
            .legenda(pub.getLegenda())
            .urlMidia(pub.getUrlMidia())
            .dataPublicacao(pub.getDataPublicacao())
            .autor(UsuarioResponse.toDTO(pub.getAutor()))
            .totalReacoes(pub.getReacoes() != null ? pub.getReacoes().size() : 0)
            .totalComentarios(pub.getComentarios() != null ? pub.getComentarios().size() : 0)
            .reacoes(pub.getReacoes() != null ? pub.getReacoes().stream().map(ReacaoDetailsResponse::toDTO).collect(Collectors.toList()) : List.of())
            .build();

    }*/
}

