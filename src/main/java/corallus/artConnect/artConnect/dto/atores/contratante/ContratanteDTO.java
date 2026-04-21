package corallus.artConnect.artConnect.dto.atores.contratante;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.contato.Contato;

public record ContratanteDTO(
    // USUARIO
    Long id,
    String nome,
    String email,
    String tipoConta,
    Status status,
    LocalDateTime dataCriacao,

    // CONTRATANTE
    
    String cpf,
    Character sexo,
    LocalDate dataNasc,
    String razaoSocial,
    String cnpj,

    String textoBio,
    Set<Seguida> seguidores,
    Set<Seguida> seguido,
    List<Contato> contatos
) {
    public static ContratanteDTO toDTO(Contratante m) {
    ContratanteDTO dto = new ContratanteDTO(m.getId(), m.getNome(), m.getEmail(),
    m.getTipoConta(), m.getStatus(), m.getDataCriacao(), m.getCpf(), m.getSexo(), m.getDataNasc(), m.getRazaoSocial(),
    m.getCnpj(), m.getTextoBio(), m.getSeguidores(), m.getSeguido(), m.getContatos());
    return dto;

    }
}
