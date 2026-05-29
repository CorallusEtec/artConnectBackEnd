package corallus.artConnect.artConnect.specification;

import corallus.artConnect.artConnect.entity.Publicacao;
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
}
