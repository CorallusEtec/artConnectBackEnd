package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static corallus.artConnect.artConnect.specification.UsuarioSpec.*;

public class UsuarioFindAllQF {
    private String nome;
    private String tipoConta;
    private String cidade;
    private String uf;
    private LocalDateTime dataCriacaoStarts;
    private LocalDateTime dataCriacaoEnds;

    public Specification<Usuario> toSpecifications() {
        return nomeContains(nome)
                .and(tipoContaContains(tipoConta))
                .and(cidadeContains(cidade))
                .and(ufContains(uf))
                .and(dataCriacaoStarts(dataCriacaoStarts))
                .and(dataCriacaoEnds(dataCriacaoEnds));
    }

    // GET E SET

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
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

    public LocalDateTime getDataCriacaoEnds() {
        return dataCriacaoEnds;
    }

    public void setDataCriacaoEnds(LocalDateTime dataCriacaoEnds) {
        this.dataCriacaoEnds = dataCriacaoEnds;
    }

    public LocalDateTime getDataCriacaoStarts() {
        return dataCriacaoStarts;
    }

    public void setDataCriacaoStarts(LocalDateTime dataCriacaoStarts) {
        this.dataCriacaoStarts = dataCriacaoStarts;
    }
}
