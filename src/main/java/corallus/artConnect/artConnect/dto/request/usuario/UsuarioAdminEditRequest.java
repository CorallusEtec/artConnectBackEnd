package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.entity.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record UsuarioAdminEditRequest(
        @NotBlank(message = "O nome está vazio")
        String nome,
        @Email(message = "O email não é valido")
        String email,

        @NotNull
        Status status
) {
}
