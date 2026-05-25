package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.atores.Artista;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class ArtistaSpec {

    public static Specification<Artista> nomeContains(String nome) {
        return (root, query, build) -> {
          if(ObjectUtils.isEmpty(nome)) {
              return null;
          } else {
              return build.like(root.get("nome"), "%"+nome+"%");
          }
        };
    }

    public static Specification<Artista> arteContains(String arte) {
        return (root,query, builder) -> {
            if(ObjectUtils.isEmpty(arte)) {
                return null;
            } else {
                return root.join("arte").get("nomeArte").in(arte);
            }

        };

    }
}
