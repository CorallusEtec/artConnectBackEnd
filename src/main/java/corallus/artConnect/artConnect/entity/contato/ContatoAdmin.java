package corallus.artConnect.artConnect.entity.contato;

import corallus.artConnect.artConnect.entity.Admin;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ContatoAdmin extends Contato{
    @ManyToOne
    private Admin admin;

    // CONSTRUTOR

    public ContatoAdmin() {

    }
    public ContatoAdmin(Long id, String valorContato, TipoContato tipoContato, Admin admin) {
        super(id, valorContato, tipoContato);
        this.admin = admin;
    }

    // GET E SET

    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    
}
