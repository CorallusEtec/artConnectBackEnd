package corallus.artConnect.artConnect.entity.reacao;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity(name = "reacao")
public class Reacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TipoReacao tipoReacao;

    private Boolean ativo;

    @JsonIgnoreProperties({"publicacoes", "reacoes"})
    @ManyToOne
    private Usuario usuario;
    @JsonIgnoreProperties({"usuario", "reacoes"})
    @ManyToOne
    @JsonIgnore
    private Comentario comentario;

    private LocalDateTime dataReacao = LocalDateTime.now();
    @ManyToOne
    private Publicacao publicacao;
   
    // CONSTRUTOR

    public Reacao() {}

   public Reacao(Long id, TipoReacao tipoReacao, Boolean ativo, Usuario usuario, Comentario comentario,
            LocalDateTime dataReacao, Publicacao publicacao) {
        this.id = id;
        this.tipoReacao = tipoReacao;
        this.ativo = ativo;
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

    public TipoReacao getTipoReacao() {
        return tipoReacao;
    }

    public void setTipoReacao(TipoReacao tipoReacao) {
        this.tipoReacao = tipoReacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
