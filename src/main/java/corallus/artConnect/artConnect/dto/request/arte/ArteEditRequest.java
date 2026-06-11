package corallus.artConnect.artConnect.dto.request.arte;

import jakarta.validation.constraints.NotBlank;

public record ArteEditRequest(
        @NotBlank(message = "O nome da arte não pode estar vazio")
        String nomeArte
) {}
