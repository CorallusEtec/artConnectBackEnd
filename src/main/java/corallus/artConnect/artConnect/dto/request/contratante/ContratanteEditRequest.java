package corallus.artConnect.artConnect.dto.request.contratante;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.contato.Contato;

public record ContratanteEditRequest(
    // USUARIO
    String nome,
    String textoBio,
    List<Contato> contatos,

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
