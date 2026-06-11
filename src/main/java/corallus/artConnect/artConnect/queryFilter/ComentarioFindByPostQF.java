package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.Comentario;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.ComentarioSpec.tipoStatusEquals;

public class ComentarioFindByPostQF {
    private String tipoStatus;

    public Specification<Comentario> getSpecification() {
        return tipoStatusEquals(tipoStatus);
    }
}
