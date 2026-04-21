package corallus.artConnect.artConnect.dto.atores.contratante;

public record ContratanteCadastroDTO(
    String nome,
    
    String email,
    String senha,

    String razaoSocial,
    String cnpj
) {}
