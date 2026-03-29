package corallus.artConnect.artConnect.dto.contratante;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.Contratante;
import corallus.artConnect.artConnect.entity.contato.ContatoContratante;

public record ContratanteDTO(
    String nome,
    String email,
    String razaoSocial,
    String statusConta,
    LocalDate dataNasc,
    Character sexo,
    String tipoConta,
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
    public static ContratanteDTO convertEntity(Contratante c) {
        ContratanteDTO dto = new ContratanteDTO(c.getNome(), c.getEmail(),
        c.getRazaoSocial(), c.getStatusConta(), c.getDataNasc(), c.getSexo(), 
        c.getTipoConta(), c.getNomeLog(), c.getNumLog(), c.getCep(), c.getBairro(), c.getComplemento(),
        c.getCidade(), c.getUf(), c.getContatos());
        return dto;
    }
}
