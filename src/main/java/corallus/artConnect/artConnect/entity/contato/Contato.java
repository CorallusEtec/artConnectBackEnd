package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.*;

@Entity
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private String valorContato;

    @ManyToOne
    private TipoContato tipoContato;

    // CONSTRUTOR

    public Contato() {}

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
