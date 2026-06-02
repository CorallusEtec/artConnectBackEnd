package corallus.artConnect.artConnect.entity.status;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.persistence.*;

@Entity
public class TipoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoStatus nomeTipoStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoStatus")
    private List<Status> status;
    // CONSTRUTOR

    public TipoStatus() {}

    public TipoStatus(Long id, ETipoStatus nomeTipoStatus) {
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

    public ETipoStatus getNomeTipoStatus() {
        return nomeTipoStatus;
    }

    public void setNomeTipoStatus(ETipoStatus nomeTipoStatus) {
        this.nomeTipoStatus = nomeTipoStatus;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}