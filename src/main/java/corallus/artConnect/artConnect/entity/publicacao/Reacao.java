package corallus.artConnect.artConnect.entity.publicacao;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.entity.Contratante;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean like;
    private Boolean deslike;
    
    @ManyToOne
    private Artista artista;
    
    @ManyToOne
    private Contratante contratante;

    @ManyToOne
    private Comentario comentario;

    @ManyToOne
    private Publicacao publicacao;

    // CONSTRUTOR

    public Reacao() {
    }

    public Reacao(Long id, Boolean like, Boolean deslike, Artista artista, Contratante contratante,
            Comentario comentario, Publicacao publicacao) {
        this.id = id;
        this.like = like;
        this.deslike = deslike;
        this.artista = artista;
        this.contratante = contratante;
        this.comentario = comentario;
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    
}
