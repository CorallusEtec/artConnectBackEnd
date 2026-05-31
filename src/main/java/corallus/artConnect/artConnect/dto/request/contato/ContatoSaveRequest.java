package corallus.artConnect.artConnect.dto.request.contato;

import jakarta.validation.constraints.NotEmpty;

public record ContatoSaveRequest(
        @NotEmpty(message = "Não foi possível salvar o contato")
        Long idUsuario,
        @NotEmpty(message = "Não foi possível salvar o contato")
        Long idTipoContato,

        String valorContato
) {
    
}
