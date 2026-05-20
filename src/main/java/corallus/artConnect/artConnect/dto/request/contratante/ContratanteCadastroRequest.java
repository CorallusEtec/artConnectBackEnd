package corallus.artConnect.artConnect.dto.request.contratante;

public record ContratanteCadastroRequest(
    String nome,
    
    String email,
    String senha,

    String razaoSocial,
    String cnpj
) {}
