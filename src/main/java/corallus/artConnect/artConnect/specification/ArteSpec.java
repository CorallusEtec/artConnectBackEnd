package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.arte.Arte;
import jakarta.persistence.criteria.Expression;
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

    public static Specification<Arte> descByArtistas() {


        return (root, query, criteriaBuilder) ->{
            Expression<Integer> quantidadeArtes = criteriaBuilder.size(root.get("artistas"));

            query.orderBy(criteriaBuilder.desc(quantidadeArtes));

            return criteriaBuilder.conjunction();
        };
    }
}
