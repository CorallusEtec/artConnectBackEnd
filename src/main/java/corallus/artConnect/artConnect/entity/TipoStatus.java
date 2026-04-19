package corallus.artConnect.artConnect.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeTipoString;

    @OneToMany(mappedBy = "tipoStatus")
    private List<Status> status;
    // CONSTRUTOR

    public TipoStatus() {}

    public TipoStatus(Long id, String nomeTipoString) {
        this.id = id;
        this.nomeTipoString = nomeTipoString;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTipoString() {
        return nomeTipoString;
    }

    public void setNomeTipoString(String nomeTipoString) {
        this.nomeTipoString = nomeTipoString;
    }

    
    
}