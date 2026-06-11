package corallus.artConnect.artConnect.dto.request.arte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeneroArteSaveRequest(
    @NotBlank(message = "O nome do gênero não pode estar vazio")
    String nomeGeneroArte,

    @NotNull(message = "O ID da arte não pode ser nulo")
    Long arteId
) {}