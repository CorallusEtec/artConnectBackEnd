package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Status statusPublicacao;

    @ManyToOne
    @JsonIgnoreProperties({"publicacoes", "reacoes", "comentarios"})
    private Usuario autor;
    
    private LocalDateTime dataPublicacao = LocalDateTime.now();

    @OneToMany(mappedBy = "publicacao")
    private Set<Reacao> reacoes;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios;
    
    private String legenda;
    private String urlMidia;
    
    // CONSTRUTOR

    public Publicacao() {}

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatusPublicacao() {
        return statusPublicacao;
    }

    public void setStatusPublicacao(Status statusPublicacao) {
        this.statusPublicacao = statusPublicacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Set<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(Set<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getUrlMidia() {
        return urlMidia;
    }

    public void setUrlMidia(String urlMidia) {
        this.urlMidia = urlMidia;
    }
}
