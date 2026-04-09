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

    @OneToMany(mappedBy = "tipoContatoAdmin")
    private List<ContatoAdmin> contatosAdmin;

    @OneToMany(mappedBy = "tipoContatoArtista")
    private List<ContatoArtista> contatosArtista;

    @OneToMany(mappedBy = "tipoContatoContratante")
    private List<ContatoContratante> contatosContratante;

    // CONSTRUTOR

    public TipoContato() {
    }

    public TipoContato(Long id, String tipoContato, List<ContatoAdmin> contatosAdmin,
            List<ContatoArtista> contatosArtista, List<ContatoContratante> contatosContratante) {
        this.id = id;
        this.tipoContato = tipoContato;
        this.contatosAdmin = contatosAdmin;
        this.contatosArtista = contatosArtista;
        this.contatosContratante = contatosContratante;
    }

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

    public List<ContatoAdmin> getContatosAdmin() {
        return contatosAdmin;
    }

    public void setContatosAdmin(List<ContatoAdmin> contatosAdmin) {
        this.contatosAdmin = contatosAdmin;
    }

    public List<ContatoArtista> getContatosArtista() {
        return contatosArtista;
    }

    public void setContatosArtista(List<ContatoArtista> contatosArtista) {
        this.contatosArtista = contatosArtista;
    }

    public List<ContatoContratante> getContatosContratante() {
        return contatosContratante;
    }

    public void setContatosContratante(List<ContatoContratante> contatosContratante) {
        this.contatosContratante = contatosContratante;
    }


    // GET E SET

 

    
}
