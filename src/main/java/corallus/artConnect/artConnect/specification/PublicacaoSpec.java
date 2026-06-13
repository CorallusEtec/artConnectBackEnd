package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public class PublicacaoSpec {

    /**
     *
     * Ordem por like
     * Recentes
     */

    public static Specification<Publicacao> legendaContains(String legenda) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(legenda)) {
                return null;
            } else {
                return builder.like(root.get("legenda"), "%" + legenda + "%");
            }
        };
    }

    public static Specification<Publicacao> dataInicioEquals(LocalDateTime dataInicio) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(dataInicio)) {
                return null;
            } else {
                return builder.greaterThanOrEqualTo(root.get("dataPublicacao"), dataInicio);
            }
        };
    }

    public static Specification<Publicacao> dataFimEquals(LocalDateTime dataFim) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(dataFim)) {
                return null;
            } else {
                return builder.lessThanOrEqualTo(root.get("dataPublicacao"), dataFim);
            }
        };
    }

    public static Specification<Publicacao> nomeAutorContains(String nomeAutor) {
        return (root, query, builder) -> {
            if(ObjectUtils.isEmpty(nomeAutor)) {
                return null;
            } else {
                return builder.like(root.join("autor").get("nome"), nomeAutor+"%");
            }
        };
    }
    public static Specification<Publicacao> tipoStatusEquals(String tipoStatus) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(tipoStatus)) {
                return root.join("statusPublicacao")
                        .get("tipoStatus").equalTo(ETipoStatus.ATIVO);
            } else {
                return root.join("statusPublicacao")
                        .get("tipoStatus").equalTo(ETipoStatus.valueOf(tipoStatus));
            }
        };
    }

    public static Specification<Publicacao> idUsuarioEquals(Long idUsuario) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(idUsuario)) {
                return null;
            } else {
                return criteriaBuilder.equal(root.join("autor").get("id"), idUsuario);
            }
        };
    }
}
