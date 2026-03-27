package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Contratante;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoContratante extends Contato {
    
    @ManyToOne
    private Contratante contratante;

    // CONSTRUTOR

    public ContatoContratante() {
    }

    public ContatoContratante(Long id, String valorContato, TipoContato tipoContato, Contratante contratante) {
        super(id, valorContato, tipoContato);
        this.contratante = contratante;
    }

    // GET E SET

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }
}
