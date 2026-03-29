package corallus.artConnect.artConnect.dto.contratante;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.contato.ContatoContratante;

public record ContratanteCadastroDTO(
    String nome,
    String razaoSocial,
    Character sexo,
    String email,
    String senha,
    LocalDate dataNasc,
    // LOGRADOURO / ENDEREÇO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,
    List<ContatoContratante> contatos
) {
    /* IMPLEMENTAR FUTURAMENTE
        public static Contratante convertDTO(ContratanteDTO dto) {
        
        }
    
    */
    
}
