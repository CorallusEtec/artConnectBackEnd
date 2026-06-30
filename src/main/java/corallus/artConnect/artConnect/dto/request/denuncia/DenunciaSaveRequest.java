package corallus.artConnect.artConnect.dto.request.denuncia;

import corallus.artConnect.artConnect.enumeration.ETipoDenuncia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DenunciaSaveRequest(
        String titulo,
        @NotNull(message = "O tipo de denuncia está vazio")
        @Pattern(regexp = ETipoDenuncia.tipoDenunciaRegEx, message = "Tipo de Denúncia invalido")
        String tipoDenuncia,
        @NotNull(message = "O id do recurso está vazio")
        Long idRecurso
) {
}
