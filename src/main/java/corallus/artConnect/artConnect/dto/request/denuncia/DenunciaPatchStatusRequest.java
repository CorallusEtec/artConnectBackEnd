package corallus.artConnect.artConnect.dto.request.denuncia;

import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DenunciaPatchStatusRequest (
        @NotNull(message = "O tipo do status não pode ser vazio")
        @Pattern(regexp = ETipoStatus.tipoStatusDenunciaRegEx, message = "Tipo de status da denuncia inválido")
        String tipoStatus
) {}
