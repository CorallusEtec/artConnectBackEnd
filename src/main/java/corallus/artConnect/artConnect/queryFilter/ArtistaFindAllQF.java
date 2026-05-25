package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Artista;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ArtistaSpec.*;

public class ArtistaFindAllQF {
    private String nome;
    private String arte;

    public Specification<Artista> toSpecification() {
          return nomeContains(nome)
                  .and(nomeContains(arte));
    }

    // GET E SET


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArte() {
        return arte;
    }

    public void setArte(String arte) {
        this.arte = arte;
    }
}
