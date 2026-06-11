package corallus.artConnect.artConnect.dto.request.tipoContato;

import jakarta.validation.constraints.NotBlank;

public record TipoContatoSaveRequest(

        @NotBlank(message = "O tipo de contato não pode estar vazio")
        String tipoContato
) {}
