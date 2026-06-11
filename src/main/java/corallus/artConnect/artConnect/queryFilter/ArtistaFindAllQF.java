package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Artista;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import static corallus.artConnect.artConnect.specification.ArtistaSpec.*;

@Getter @Setter
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
}
