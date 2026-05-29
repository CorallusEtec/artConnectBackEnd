package corallus.artConnect.artConnect.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 
 * USAR ESSE RECORD PARA AUTENTICAÇÃO
 *
 */  
public record UserLoginRequest(
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    String email,

    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 carateres")
    String senha
) {
    
}
