package corallus.artConnect.artConnect.entity.status;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Status {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private TipoStatus tipoStatus;
    private String descricao;
    private LocalDateTime dataModificacao;


    // CONSTRUTOR

    public Status() {}

    public Status(Long id, TipoStatus tipoStatus, String descricao, LocalDateTime dataModificacao) {
        this.id = id;
        this.tipoStatus = tipoStatus;
        this.descricao = descricao;
        this.dataModificacao = dataModificacao;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoStatus getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(TipoStatus tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }



    
}
