package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class ComentarioSpec {

    public static Specification<Comentario> tipoStatusEquals(String tipoStatus) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(tipoStatus)) {
               return root
                       .join("status")
                       .get("tipoStatus").equalTo(ETipoStatus.ATIVO);
            } else {
                return root
                    .join("status")
                    .get("tipoStatus").equalTo(ETipoStatus.valueOf(tipoStatus));
            }
        };
    }
}
