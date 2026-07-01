package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.Denuncia;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class DenunciaSpec {

    public static Specification<Denuncia> tipoStatusContains(String tipoStatus) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(tipoStatus)) {
                return null;
            } else {
                return root.join("status")
                        .get("tipoStatus").equalTo(ETipoStatus.valueOf(tipoStatus));
            }
        };
    }
}
