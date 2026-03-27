package corallus.artConnect.artConnect.entity.contato;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipoContato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoContato;

    @OneToMany(mappedBy = "tipoContato")
    private List<Contato> contatos;

    // CONSTRUTOR

    public TipoContato() {
    }

    public TipoContato(Long id, String tipoContato, List<Contato> contatos) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contatos = contatos;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    
}
