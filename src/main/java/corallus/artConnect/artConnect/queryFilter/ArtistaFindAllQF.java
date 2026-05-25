package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Artista;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ArtistaSpec.*;

public class ArtistaFindAllQF {
    private String nome;
    private String arte;
    private String nomeArtistico;
    private String cidade;
    private String uf;

    public Specification<Artista> toSpecification() {
        return nomeContains(nome)
                .and(arteContains(arte))
                .and(nomeArtisitcoContains(nomeArtistico))
                .and(cidadeContains(cidade))
                .and(ufContains(uf));
    }

    // GET E SET

    public String getArte() {
        return arte;
    }

    public void setArte(String arte) {
        this.arte = arte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
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
