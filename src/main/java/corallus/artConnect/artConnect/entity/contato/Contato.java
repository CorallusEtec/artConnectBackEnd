package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Usuario usuario;

    private String valorContato;
    

    private TipoContato tipoContato;

    // CONSTRUTOR

    public Contato() {

    }

    public Contato(Long id, Usuario usuario, String valorContato, TipoContato tipoContato) {
        this.id = id;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
