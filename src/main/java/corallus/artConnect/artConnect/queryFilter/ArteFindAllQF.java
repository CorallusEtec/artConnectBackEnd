package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.arte.Arte;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ArteSpec.*;

@Getter @Setter
public class ArteFindAllQF {
    private String nomeArte;

    public Specification<Arte> getSpecification() {
        return nomeArteContains(nomeArte);
    }
}
