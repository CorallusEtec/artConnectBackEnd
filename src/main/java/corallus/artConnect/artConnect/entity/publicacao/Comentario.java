package corallus.artConnect.artConnect.entity.publicacao;

import java.util.List;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.entity.Contratante;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusComentario;
    private String mensagem;

    @ManyToOne
    private Artista artista;

    @ManyToOne
    private Contratante contratante;

    @ManyToOne
    private Publicacao publicacao;

    @OneToMany(mappedBy = "comentario")
    private List<Reacao> reacoes;

    // CONSTRUTOR

    public Comentario() {

    }

    public Comentario(Long id, String statusComentario, String mensagem, Artista artista, Contratante contratante,
            Publicacao publicacao, List<Reacao> reacoes) {
        this.id = id;
        this.statusComentario = statusComentario;
        this.mensagem = mensagem;
        this.artista = artista;
        this.contratante = contratante;
        this.publicacao = publicacao;
        this.reacoes = reacoes;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusComentario() {
        return statusComentario;
    }

    public void setStatusComentario(String statusComentario) {
        this.statusComentario = statusComentario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public List<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    

}
