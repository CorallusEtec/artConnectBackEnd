package corallus.artConnect.artConnect.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Arte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArte;

    @OneToMany(mappedBy = "arte")
    private List<Artista> artistas;

    // CONSTRUTOR

    public Arte() {
    }

    public Arte(Long id, String nomeArte, List<Artista> artistas) {
        this.id = id;
        this.nomeArte = nomeArte;
        this.artistas = artistas;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArte() {
        return nomeArte;
    }

    public void setNomeArte(String nomeArte) {
        this.nomeArte = nomeArte;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    
}
