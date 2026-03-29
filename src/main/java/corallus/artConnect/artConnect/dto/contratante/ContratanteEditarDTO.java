package corallus.artConnect.artConnect.dto.contratante;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.contato.ContatoContratante;

public record ContratanteEditarDTO(
    String nome,
    String razaoSocial,
    List<ContatoContratante> contatos,
    Character sexo,
    LocalDate dataNasc,

    // LOGRADOURO / ENDEREÇO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf
) {}
