package corallus.artConnect.artConnect.entity;

import corallus.artConnect.artConnect.entity.atores.Artista;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nomeTag;

    @ManyToOne
    private Artista artista;

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTag() {
        return nomeTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    

    // CONSTRUTOR
    
    public Tag() {
        
    }

    public Tag(Long id, String nomeTag, Artista artista) {
        this.id = id;
        this.nomeTag = nomeTag;
        this.artista = artista;
    }
}
