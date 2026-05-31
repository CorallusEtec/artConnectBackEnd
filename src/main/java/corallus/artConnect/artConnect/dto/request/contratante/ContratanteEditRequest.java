package corallus.artConnect.artConnect.dto.request.contratante;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record ContratanteEditRequest(
    // USUARIO

    @NotBlank(message = "O nome não pode ser vazio")
    String nome,
    String textoBio,

    // CONTRATANTE
    LocalDate dataNasc,
    String razaoSocial,
    Character sexo,

    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf
) {}
