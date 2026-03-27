package corallus.artConnect.artConnect.entity;

import java.util.List;

import corallus.artConnect.artConnect.entity.contato.ContatoAdmin;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Admin extends Usuario {

    @OneToMany(mappedBy = "admin")
    private List<ContatoAdmin> contatos;

    // CONSTRUTOR

    public Admin() {
    }   

    public Admin(Long id, String nome, String email, String senha, String tipoConta, String nomeLog, Short numLog,
            String cep, String bairro, String complemento, String cidade, String uf, List<ContatoAdmin> contatos) {
        super(id, nome, email, senha, tipoConta, nomeLog, numLog, cep, bairro, complemento, cidade, uf);
        this.contatos = contatos;
    }

    // GET E SET

    public List<ContatoAdmin> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoAdmin> contatos) {
        this.contatos = contatos;
    }

   


    
}
