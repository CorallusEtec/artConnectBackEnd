package corallus.artConnect.artConnect.dto.request.reacao;

import jakarta.validation.constraints.NotBlank;

public record ReacaoRequest(
        @NotBlank(message = "Não foi possível realizar essa ação")
        String nomeTipoReacao,
        @NotBlank(message = "Não foi possível realizar essa ação")
        Long idAutor
) {
    
}
