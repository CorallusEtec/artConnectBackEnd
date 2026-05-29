package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ContratanteSpec.*;

public class ContratanteFindAllQF {

    private String nome;
    private String tipoContratante;
    private String cidade;
    private String uf;


    public Specification<Contratante> toSpecification() {
        return nomeContains(nome)
                .and(tipoContratanteContains(tipoContratante))
                .and(cidadeContains(cidade))
                .and(ufContains(uf));
    }


    // GET E SET


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoContratante() {
        return tipoContratante;
    }

    public void setTipoContratante(String tipoContratante) {
        this.tipoContratante = tipoContratante;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
