package corallus.artConnect.artConnect.dto.request.contato;

import jakarta.validation.constraints.NotBlank;

public record ContatoSaveRequest(
        @NotBlank(message = "Não foi possível salvar o contato")
        Long idUsuario,
        @NotBlank(message = "Não foi possível salvar o contato")
        Long idTipoContato,

        String valorContato
) {
    
}
