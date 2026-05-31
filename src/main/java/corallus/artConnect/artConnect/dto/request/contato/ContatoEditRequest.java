package corallus.artConnect.artConnect.dto.request.contato;

import jakarta.validation.constraints.NotBlank;

public record ContatoEditRequest(
        @NotBlank(message = "O valor do contato não pode estar vazio")
        String valorContato
) {
}