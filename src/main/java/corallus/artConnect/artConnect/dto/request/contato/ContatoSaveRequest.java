package corallus.artConnect.artConnect.dto.request.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoSaveRequest(
        @NotNull(message = "Não foi possível salvar o contato")
        Long idTipoContato,

        String valorContato
) {
    
}
