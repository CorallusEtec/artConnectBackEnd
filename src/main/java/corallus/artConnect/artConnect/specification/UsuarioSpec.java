package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(tipoContaString)) {
                return  null;
            } else {
                ETipoConta tipoConta = ETipoConta.valueOf(tipoContaString);
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

    public static Specification<Usuario> notAdmin() {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .notEqual(root.get("tipoConta"), ETipoConta.ADMIN);

    }

    public static Specification<Usuario> tipoStatusContains(String tipoStatus) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(tipoStatus)) {
                return criteriaBuilder
                        .equal(root.join("status").get("tipoStatus"), ETipoStatus.ATIVO);
            } else {
                ETipoStatus tipoEnum = ETipoStatus.valueOf(tipoStatus);
                return criteriaBuilder.equal(root.join("status").get("tipoStatus"), tipoEnum);
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

    public static Specification<Usuario> nomeArteContains(String nomeArte) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(nomeArte)) {
                return null;
            } else {
                return criteriaBuilder.like(root.join("arte").get("nomeArte"), nomeArte);
            }
        };
    }

    public static Specification<Usuario> generosArteContains(List<String> generosArte) {
        return (root, query, cb) -> {
            if(ObjectUtils.isEmpty(generosArte)) {
                return null;
            } else {
                query.distinct(true);
                // Cria a subquery para contar as categorias
                Subquery<Long> sub = query.subquery(Long.class);
                Root<Usuario> subRoot = sub.from(Usuario.class);

                sub.select(cb.count(subRoot) )
                        .where(cb.equal(subRoot.get("id"), root.get("id")), subRoot.join("generosArte").get("id").in(generosArte));

                return cb.equal(sub, (long) generosArte.size());
            }
        };
    }
}
