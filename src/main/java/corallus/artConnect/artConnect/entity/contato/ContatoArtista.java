package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Artista;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoArtista extends Contato {
    
    @ManyToOne
    private Artista artista;

    @ManyToOne
    private TipoContato tipoContatoArtista;

    // CONSTRUTOR

    public ContatoArtista() {
        
    }
    public ContatoArtista(Long id, String valorContato, TipoContato tipoContato, Artista artista,
            TipoContato tipoContatoArtista) {
        super(id, valorContato, tipoContato);
        this.artista = artista;
        this.tipoContatoArtista = tipoContatoArtista;
    }


    // GET E SET

   

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    public TipoContato getTipoContatoArtista() {
        return tipoContatoArtista;
    }
    public void setTipoContatoArtista(TipoContato tipoContatoArtista) {
        this.tipoContatoArtista = tipoContatoArtista;
    }


    
}
