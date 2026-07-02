package corallus.artConnect.artConnect.dto.request.denuncia;

import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DenunciaAdminPatchStatus(
        @Pattern(regexp = ETipoStatus.tipoStatusDenunciaRegEx, message = "Tipo de status inválido")
        @NotNull
        String tipoStatus
) {
}
