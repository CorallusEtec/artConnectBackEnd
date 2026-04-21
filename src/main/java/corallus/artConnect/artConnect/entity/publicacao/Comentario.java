package corallus.artConnect.artConnect.entity.publicacao;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import corallus.artConnect.artConnect.entity.Status;

import corallus.artConnect.artConnect.entity.atores.Usuario;
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
    @JsonIgnoreProperties({"seguidores", "seguido", "reacoes", "publicacoes", "contatos"})
    private Usuario usuario;

    @ManyToOne
    private Publicacao publicacao;
    
    @OneToMany(mappedBy = "comentario")
    private Set<Reacao> reacoes;
    
    // CONSTRUTOR

    public Comentario() {}

    public Comentario(Long id, Status statusComentario, LocalDateTime dataComentario, String mensagem, Usuario usuario,
            Set<Reacao> reacoes, Publicacao publicacao) {
        this.id = id;
        this.statusComentario = statusComentario;
        this.dataComentario = dataComentario;
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.reacoes = reacoes;
        this.publicacao = publicacao;
    }

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

    public Set<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(Set<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

  

    

}
