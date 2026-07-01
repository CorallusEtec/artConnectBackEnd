package corallus.artConnect.artConnect.dto.request.publicacao;

import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PublicacaoAdminPatchStatusRequest(
        @Pattern(regexp = ETipoStatus.tipoStatusPublicacaoRegEx, message = "Tipo de status inválido")
        @NotNull
        String tipoStatus
) {
}
