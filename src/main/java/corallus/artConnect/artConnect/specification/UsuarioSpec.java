package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class UsuarioSpec {

    public static Specification<Usuario> nomeContains(String nome) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(nome)) {
                return  null;
            } else {
                return builder.like(root.get("nome"), nome+"%");
            }
        };
    }

    public static Specification<Usuario> tipoContaContains(String tipoConta) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(tipoConta)) {
                return  null;
            } else {
                return builder.like(root.get("tipoConta"), tipoConta+"%");
            }
        };
    }

    public static Specification<Usuario> cidadeContains(String cidade) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(cidade)) {
                return null;
            } else {
                return build.like(root.get("cidade"), "%"+cidade+"%");
            }
        };
    }

    public static Specification<Usuario> ufContains(String uf) {
        return (root, query, build) -> {
            if(ObjectUtils.isEmpty(uf)) {
                return null;
            } else {
                return build.like(root.get("uf"), uf+"%");
            }
        };
    }

}
