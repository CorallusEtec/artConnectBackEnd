package corallus.artConnect.artConnect.dto.request.reacao;

import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import jakarta.validation.constraints.NotBlank;

public record ReacaoRequest(
        @NotBlank(message = "Não foi possível realizar essa ação")
        ETipoReacao nomeTipoReacao,

        @NotBlank(message = "Não foi possível realizar essa ação")
        Long idAutor
) {
    
}
