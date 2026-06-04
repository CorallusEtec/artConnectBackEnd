package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import static corallus.artConnect.artConnect.specification.ContratanteSpec.*;

@Getter @Setter
public class ContratanteFindAllQF {
    private String nome;
    private String cidade;
    private String uf;

    public Specification<Contratante> toSpecification() {
        return nomeContains(nome)
                .and(cidadeContains(cidade))
                .and(ufContains(uf));
    }
}
