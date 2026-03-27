package corallus.artConnect.artConnect.entity.publicacao;

import java.util.List;

import corallus.artConnect.artConnect.entity.Artista;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statusPublicacao;
    
    @ManyToOne
    private Artista autor;
    
    @OneToMany(mappedBy = "publicacao")
    private List<Reacao> reacoes;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios;


    private String mensagem;
    
    private String urlFoto;
    private String urlVideo;
    private String urlAudio;

    // CONSTRUTOR

    public Publicacao() {
    }

    public Publicacao(Long id, String statusPublicacao, Artista autor, List<Reacao> reacoes,
            List<Comentario> comentarios, String mensagem, String urlFoto, String urlVideo, String urlAudio) {
        this.id = id;
        this.statusPublicacao = statusPublicacao;
        this.autor = autor;
        this.reacoes = reacoes;
        this.comentarios = comentarios;
        this.mensagem = mensagem;
        this.urlFoto = urlFoto;
        this.urlVideo = urlVideo;
        this.urlAudio = urlAudio;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusPublicacao() {
        return statusPublicacao;
    }

    public void setStatusPublicacao(String statusPublicacao) {
        this.statusPublicacao = statusPublicacao;
    }

    public Artista getAutor() {
        return autor;
    }

    public void setAutor(Artista autor) {
        this.autor = autor;
    }

    public List<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    

}
