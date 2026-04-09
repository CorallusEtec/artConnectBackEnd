package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Contratante;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoContratante extends Contato {
    
    @ManyToOne
    private Contratante contratante;

    @ManyToOne
    private TipoContato tipoContatoContratante;

    // CONSTRUTOR

    public ContatoContratante() {
    }

    public ContatoContratante(Long id, String valorContato, TipoContato tipoContato, Contratante contratante,
            TipoContato tipoContatoContratante) {
        super(id, valorContato, tipoContato);
        this.contratante = contratante;
        this.tipoContatoContratante = tipoContatoContratante;
    }

    public Contratante getContratante() {
        return contratante;
    }

   

    // GET E SET

     public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public TipoContato getTipoContatoContratante() {
        return tipoContatoContratante;
    }

    public void setTipoContatoContratante(TipoContato tipoContatoContratante) {
        this.tipoContatoContratante = tipoContatoContratante;
    }
    
}
