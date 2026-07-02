package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.Denuncia;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static corallus.artConnect.artConnect.specification.DenunciaSpec.tipoStatusContains;

@Getter @Setter
public class DenunciaFindAllQF {

    private String tipoStatus;

    public Specification<Denuncia> getSpecification() {
        return tipoStatusContains(tipoStatus);
    }
}
