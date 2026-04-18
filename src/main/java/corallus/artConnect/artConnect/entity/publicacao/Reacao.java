package corallus.artConnect.artConnect.entity.publicacao;


import java.time.LocalDateTime;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Reacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean like;
    private Boolean deslike;
    private Usuario usuario;
    private Comentario comentario;

    private LocalDateTime dataReacao = LocalDateTime.now();
    private Publicacao publicacao;
   
    // CONSTRUTOR

    public Reacao() {}

    public Reacao(Long id, Boolean like, Boolean deslike, Usuario usuario, Comentario comentario,
            LocalDateTime dataReacao, Publicacao publicacao) {
        this.id = id;
        this.like = like;
        this.deslike = deslike;
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataReacao = dataReacao;
        this.publicacao = publicacao;
    }

    // GET E SET 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Boolean getDeslike() {
        return deslike;
    }

    public void setDeslike(Boolean deslike) {
        this.deslike = deslike;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataReacao() {
        return dataReacao;
    }

    public void setDataReacao(LocalDateTime dataReacao) {
        this.dataReacao = dataReacao;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    

}
