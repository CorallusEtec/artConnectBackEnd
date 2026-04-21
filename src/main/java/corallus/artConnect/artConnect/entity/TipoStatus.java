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
    private String nomeTipoStatus;

    @OneToMany(mappedBy = "tipoStatus")
    private List<Status> status;
    // CONSTRUTOR

    public TipoStatus() {}

    public TipoStatus(Long id, String nomeTipoStatus) {
        this.id = id;
        this.nomeTipoStatus = nomeTipoStatus;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTipoString() {
        return nomeTipoStatus;
    }

    public void setNomeTipoString(String nomeTipoStatus) {
        this.nomeTipoStatus = nomeTipoStatus;
    }

    
    
}