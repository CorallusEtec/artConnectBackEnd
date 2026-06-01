package corallus.artConnect.artConnect.queryFilter;

import com.fasterxml.jackson.annotation.JsonFormat;
import corallus.artConnect.artConnect.entity.Publicacao;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import static corallus.artConnect.artConnect.specification.PublicacaoSpec.*;

public class PublicacaoFindAllQF {

    private String legenda;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFim;

    private String nomeAutor;

    public Specification<Publicacao> toSpecifications() {
        return legendaContains(legenda)
                .and(dataInicioEquals(dataInicio))
                .and(dataFimEquals(dataFim))
                .and(nomeAutorContains(nomeAutor));
    }

    // GET E SET

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
