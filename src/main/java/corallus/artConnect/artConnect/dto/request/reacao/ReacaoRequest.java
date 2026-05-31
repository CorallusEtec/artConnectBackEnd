package corallus.artConnect.artConnect.dto.request.reacao;

import jakarta.validation.constraints.NotEmpty;

public record ReacaoRequest(
        @NotEmpty(message = "Não foi possível realizar essa ação")
        String nomeTipoReacao,
        @NotEmpty(message = "Não foi possível realizar essa ação")
        Long idAutor
) {
    
}
