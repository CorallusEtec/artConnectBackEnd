package corallus.artConnect.artConnect.queryFilter;

import com.fasterxml.jackson.annotation.JsonFormat;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.domain.Specification;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;

import java.time.LocalDateTime;

import static corallus.artConnect.artConnect.specification.UsuarioSpec.*;

public class UsuarioFindAllQF {
    private String nome;
    private String tipoConta;
    private String cidade;
    private String uf;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacaoStarts;

    public Specification<Usuario> toSpecifications() {
        return nomeContains(nome)
                .and(tipoContaContains(tipoConta))
                .and(cidadeContains(cidade))
                .and(ufContains(uf))
                .and(dataCriacaoStarts(dataCriacaoStarts));
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

    public LocalDateTime getDataCriacaoStarts() {
        return dataCriacaoStarts;
    }

    public void setDataCriacaoStarts(LocalDateTime dataCriacaoStarts) {
        this.dataCriacaoStarts = dataCriacaoStarts;
    }
}
