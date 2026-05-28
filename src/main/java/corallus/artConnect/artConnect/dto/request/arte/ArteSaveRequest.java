package corallus.artConnect.artConnect.dto.request.arte;

import jakarta.validation.constraints.NotBlank;

public record ArteSaveRequest(
        @NotBlank(message = "Insira o nome da arte")
        String nomeArte
) {
}
