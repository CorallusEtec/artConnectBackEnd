package corallus.artConnect.artConnect.entity.reacao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import jakarta.persistence.*;

@Entity
public class TipoReacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoReacao nomeTipo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoReacao")
    private List<Reacao> reacoes;


    // CONSTRUTOR

    public TipoReacao() {}

    public TipoReacao(Long id, ETipoReacao nomeTipo, List<Reacao> reacoes) {
        this.id = id;
        this.nomeTipo = nomeTipo;
        this.reacoes = reacoes;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ETipoReacao getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(ETipoReacao nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public List<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }
}
