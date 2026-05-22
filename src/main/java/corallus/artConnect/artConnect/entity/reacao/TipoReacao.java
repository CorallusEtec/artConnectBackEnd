package corallus.artConnect.artConnect.entity.reacao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipoReacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeTipo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoReacao")
    private List<Reacao> reacoes;


    // CONSTRUTOR

    public TipoReacao() {

    }

    public TipoReacao(Long id, String nomeTipo, List<Reacao> reacoes) {
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

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public List<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }
}
