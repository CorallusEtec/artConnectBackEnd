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
              return build.like(root.get("nome"), nome+"%");
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

    public static Specification<Artista> nomeArtisitcoContains(String nomeArtistico) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(nomeArtistico)) {
                return null;
            } else {
                return build.like(root.get("nomeArtistico"), nomeArtistico+"%");
            }
        };
    }

    public static Specification<Artista> cidadeContains(String cidade) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(cidade)) {
                return null;
            } else {
                return build.like(root.get("cidade"), "%"+cidade+"%");
            }
        };
    }

    public static Specification<Artista> ufContains(String uf) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(uf)) {
                return null;
            } else {
                return build.like(root.get("uf"), uf+"%");
            }
        };
    }

}

