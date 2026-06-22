package corallus.artConnect.artConnect.dto.request.arte;

import jakarta.validation.constraints.NotBlank;

public record GeneroArteEditRequest (
    @NotBlank(message = "O nome do genero de arte não pode ser vazio")
    String nomeGeneroArte
) {}
