package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import java.util.List;
import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Artista extends Usuario {
    private String nomeArtistico;
    private LocalDate dataNasc;


    @JoinTable(
        name = "artista_tag",
        joinColumns = @JoinColumn(name = "artista_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ManyToMany
    private List<Tag> listaTags;

    @ManyToOne
    
    private Arte arte;

    private Character sexo;
    // CONSTRUTOR

    public Artista() {
        super();
    }

    // GET E SET

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Arte getArte() {
        return arte;
    }

    public void setArte(Arte arte) {
        this.arte = arte;
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) {
        this.listaTags = listaTags;
    }

}
