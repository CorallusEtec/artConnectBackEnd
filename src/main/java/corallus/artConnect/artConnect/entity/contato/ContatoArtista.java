package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Artista;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoArtista extends Contato {
    
    @ManyToOne
    private Artista artista;

    // CONSTRUTOR

    public ContatoArtista() {
        
    }

    public ContatoArtista(Long id, String valorContato, TipoContato tipoContato, Artista artista) {
        super(id, valorContato, tipoContato);
        this.artista = artista;
    }

    // GET E SET

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }


    
}
