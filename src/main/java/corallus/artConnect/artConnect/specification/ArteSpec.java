package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.arte.Arte;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class ArteSpec {
    public static Specification<Arte> nomeArteContains(String nomeArte) {
        return (root, query, criteriaBuilder) -> {
            if (ObjectUtils.isEmpty(nomeArte)) {
                return null;
            } else {
                return criteriaBuilder.like(root.get("nomeArte"), nomeArte+"%");
            }
        };
    }
}
