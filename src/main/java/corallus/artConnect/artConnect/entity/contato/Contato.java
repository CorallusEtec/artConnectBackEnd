package corallus.artConnect.artConnect.entity.contato;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String valorContato;
    
    @ManyToOne
    private TipoContato tipoContato;

    // CONSTRUTOR

    public Contato() {

    }

    public Contato(Long id, String valorContato, TipoContato tipoContato) {
        this.id = id;
        this.valorContato = valorContato;
        this.tipoContato = tipoContato;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValorContato() {
        return valorContato;
    }

    public void setValorContato(String valorContato) {
        this.valorContato = valorContato;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }
   
}
