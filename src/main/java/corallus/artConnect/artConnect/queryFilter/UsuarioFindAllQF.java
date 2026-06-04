package corallus.artConnect.artConnect.queryFilter;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static corallus.artConnect.artConnect.specification.UsuarioSpec.*;

@Getter @Setter
public class UsuarioFindAllQF {
    private String nome;
    private String tipoConta;
    private String cidade;
    private String uf;
    private LocalDateTime dataCriacaoStarts;
    private LocalDateTime dataCriacaoEnds;

    public Specification<Usuario> toSpecifications() {
        return nomeContains(nome)
                .and(tipoContaContains(tipoConta))
                .and(cidadeContains(cidade))
                .and(ufContains(uf))
                .and(dataCriacaoStarts(dataCriacaoStarts))
                .and(dataCriacaoEnds(dataCriacaoEnds));
    }
}
