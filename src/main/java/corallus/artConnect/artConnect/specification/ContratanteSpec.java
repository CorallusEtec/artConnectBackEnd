package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class ContratanteSpec {


    public static Specification<Contratante> nomeContains(String nome) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(nome)) {
                return null;
            } else {
                return build.like(root.get("nome"), nome+"%");
            }
        };
    }

    public static Specification<Contratante> cidadeContains(String cidade) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(cidade)) {
                return null;
            } else {
                return build.like(root.get("cidade"), "%"+cidade+"%");
            }
        };
    }

    public static Specification<Contratante> ufContains(String uf) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(uf)) {
                return null;
            } else {
                return build.like(root.get("uf"), uf+"%");
            }
        };
    }
}
