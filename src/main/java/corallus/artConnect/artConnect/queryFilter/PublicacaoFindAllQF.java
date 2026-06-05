package corallus.artConnect.artConnect.queryFilter;

import com.fasterxml.jackson.annotation.JsonFormat;
import corallus.artConnect.artConnect.entity.Publicacao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import static corallus.artConnect.artConnect.specification.PublicacaoSpec.*;

@Setter @Getter
public class PublicacaoFindAllQF {
    private String legenda;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFim;
    private String nomeAutor;
    private String nomeTipoStatus;

    public Specification<Publicacao> toSpecifications() {
        return legendaContains(legenda)
                .and(dataInicioEquals(dataInicio))
                .and(dataFimEquals(dataFim))
                .and(nomeAutorContains(nomeAutor))
                .and(nomeTipoStatusEquals(nomeAutor));
    }
}
