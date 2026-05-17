package corallus.artConnect.artConnect.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import corallus.artConnect.artConnect.entity.atores.Artista;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nomeTag;

    @ManyToMany(mappedBy = "listaTags")
    @JsonIgnore
    private List<Artista> listaArtista;

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

    public List<Artista> getListaArtista() {
        return listaArtista;
    }

    public void setListaArtista(List<Artista> listaArtista) {
        this.listaArtista = listaArtista;
    }
    
    

    // CONSTRUTOR
    
    public Tag() {
        
    }

    public Tag(Long id, String nomeTag, List<Artista> listaArtista) {
        this.id = id;
        this.nomeTag = nomeTag;
        this.listaArtista = listaArtista;
    }

   

    
 

}
