package corallus.artConnect.artConnect.dto.request.artista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ArtistaCadastroRequest(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,

        @Email(message = "O email deve ser válido")
        @NotBlank(message = "O email não pode estar vazio")
        String email,

        @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres")

        @NotBlank(message = "A senha não pode estar vazia")
        String senha
) {}
