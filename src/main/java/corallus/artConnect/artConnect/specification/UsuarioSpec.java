package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

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

    public static Specification<Usuario> tipoContaContains(String tipoContaString) {
        ETipoConta tipoConta = ETipoConta.valueOf(tipoContaString);
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

    public static Specification<Usuario> dataCriacaoStarts(LocalDateTime dataCriacao) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(dataCriacao)) {
                return null;
            } else {
                return builder.greaterThanOrEqualTo(root.get("dataCriacao"), dataCriacao);
            }
        };
    }

    public static Specification<Usuario> dataCriacaoEnds(LocalDateTime dataCriacao) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(dataCriacao)) {
                return null;
            } else {
                return builder.lessThanOrEqualTo(root.get("dataCriacao"), dataCriacao);
            }
        };
    }

}
