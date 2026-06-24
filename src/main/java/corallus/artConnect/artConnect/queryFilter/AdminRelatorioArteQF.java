package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.arte.Arte;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ArteSpec.descByArtistas;

public class AdminRelatorioArteQF {

    public Specification<Arte> getSpecification() {
        return descByArtistas();

    }

}
