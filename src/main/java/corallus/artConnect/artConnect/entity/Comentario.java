package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Status statusComentario;

    private LocalDateTime dataComentario;
    private String mensagem;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    private Publicacao publicacao;
    
    @OneToMany(mappedBy = "comentario")
    private Set<Reacao> reacoes;
    
    // CONSTRUTOR

    public Comentario() {}

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatusComentario() {
        return statusComentario;
    }

    public void setStatusComentario(Status statusComentario) {
        this.statusComentario = statusComentario;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public Set<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(Set<Reacao> reacoes) {
        this.reacoes = reacoes;
    }
}
