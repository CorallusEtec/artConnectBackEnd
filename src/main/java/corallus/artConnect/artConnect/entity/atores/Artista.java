package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;

import corallus.artConnect.artConnect.entity.arte.Arte;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class Artista extends Usuario {
    private String nomeArtistico;
    private LocalDate dataNasc;

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

}
