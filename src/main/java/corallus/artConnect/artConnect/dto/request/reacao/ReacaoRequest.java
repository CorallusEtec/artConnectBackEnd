package corallus.artConnect.artConnect.dto.request.reacao;

import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ReacaoRequest(
        @NotBlank(message = "Não foi possível realizar essa ação")
        @Pattern(regexp = ETipoReacao.tipoReacaoRegExp, message = "Tipo de reação inválida")
        String nomeTipoReacao,

        @NotNull(message = "O recurso de reação não pode ser vazio")
        Long idRecurso, // ID do que será reagido

        @NotBlank(message = "O tipo de recurso não pode ser vazio")
        @Pattern(regexp = "COMENTARIO|PUBLICACAO")
        String tipoRecurso
) {
    
}
