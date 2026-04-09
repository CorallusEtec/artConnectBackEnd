package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Admin;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoAdmin extends Contato{
    @ManyToOne
    private Admin admin;

    @ManyToOne
    private TipoContato tipoContatoAdmin;

    // CONSTRUTOR

    public ContatoAdmin() {

    }

    public ContatoAdmin(Long id, String valorContato, TipoContato tipoContato, Admin admin,
            TipoContato tipoContatoAdmin) {
        super(id, valorContato, tipoContato);
        this.admin = admin;
        this.tipoContatoAdmin = tipoContatoAdmin;
    }
    

    // GET E SET

    


    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public TipoContato getTipoContatoAdmin() {
        return tipoContatoAdmin;
    }

    public void setTipoContatoAdmin(TipoContato tipoContatoAdmin) {
        this.tipoContatoAdmin = tipoContatoAdmin;
    }


    
}
