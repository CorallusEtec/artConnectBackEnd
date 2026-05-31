package corallus.artConnect.artConnect.dto.request.contato;

import jakarta.validation.constraints.NotEmpty;

public record ContatoEditRequest(
        @NotEmpty(message = "O valor do contato não pode estar vazio")
        String valorContato
) {
}