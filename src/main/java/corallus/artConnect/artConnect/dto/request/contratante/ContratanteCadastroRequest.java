package corallus.artConnect.artConnect.dto.request.contratante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ContratanteCadastroRequest(

        @NotEmpty(message = "O nome não pode ser vazio")
        String nome,
        @Email(message = "O email deve ser válido")
        @NotEmpty(message = "O email não pode estar vazio")
        String email,

        @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @NotEmpty(message = "A senha não pode estar vazia")
        String senha,

    String razaoSocial,
    String cnpj
) {}
