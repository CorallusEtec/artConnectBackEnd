package corallus.artConnect.artConnect.dto.response.admin;

import lombok.Builder;
import java.util.List;


@Builder
public record RelatorioResponse(
       Integer artistasCadastrados,
       Integer contratantesCadastrados,
       Integer publicacoesCompartilhadas,

       List<ArteRelatorio> artes

) {}
