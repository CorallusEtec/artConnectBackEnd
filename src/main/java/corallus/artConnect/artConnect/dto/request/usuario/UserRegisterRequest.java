package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.enumeration.ETipoConta;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
    @NotBlank(message = "O nome não pode estar vazio")
    String nome,
    
    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "O email deve ser válido")
    String email,

    @Size(min = 6, message = "A senha dever ter no mínimo 6 caracteres")
    @NotBlank
    String senha,

    @NotBlank(message = "Erro ao cadastrar usuário")
    ETipoConta tipoConta,

    // Usar para Contratantes CNPJ
    String razaoSocial,
    String cnpj
) {
    
}
