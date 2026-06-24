package corallus.artConnect.artConnect.dto.response.admin;

import lombok.Builder;
import java.util.List;


@Builder
public record RelatorioResponse(
       Integer artistasCadastrados,
       Integer contratantesCadastrados,
       Integer publicacoesRealizadas,

       List<ArteRelatorio> artes,
        List<Object[]> publicacaoSemana
) {}
