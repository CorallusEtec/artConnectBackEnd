package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.entity.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record UsuarioAdminEditRequest(

        @NotNull
        Status status
) {
}
