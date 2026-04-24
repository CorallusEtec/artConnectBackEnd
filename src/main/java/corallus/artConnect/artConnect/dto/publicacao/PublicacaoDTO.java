package corallus.artConnect.artConnect.dto.publicacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public class PublicacaoDTO {

    private Long id;
    private String legenda;
    private String urlMidia;
    private LocalDateTime dataPublicacao;

    private Long autorId;
    private String autorNome;

    private int totalReacoes;
    private int totalComentarios;

    public PublicacaoDTO(Publicacao pub) {
        this.id = pub.getId();
        this.legenda = pub.getLegenda();
        this.urlMidia = pub.getUrlMidia();
        this.dataPublicacao = pub.getDataPublicacao();

        this.autorId = pub.getAutor().getId();
        this.autorNome = pub.getAutor().getNome();

        this.totalReacoes = pub.getReacoes() != null ? pub.getReacoes().size() : 0;
        this.totalComentarios = pub.getComentarios() != null ? pub.getComentarios().size() : 0;
    }

    public Long getId() {
        return id;
    }

    public String getLegenda() {
        return legenda;
    }

    public String getUrlMidia() {
        return urlMidia;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getAutorId() {
        return autorId;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public int getTotalReacoes() {
        return totalReacoes;
    }

    public int getTotalComentarios() {
        return totalComentarios;
    }
}
