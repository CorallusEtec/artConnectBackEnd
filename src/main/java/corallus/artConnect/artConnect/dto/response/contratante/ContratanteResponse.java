package corallus.artConnect.artConnect.dto.response.contratante;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;

public record ContratanteResponse(
    // USUARIO
    Long id,
    String nome,
    String email,
    ETipoConta tipoConta,
    Status status,
    LocalDateTime dataCriacao,

    // CONTRATANTE
    
    String cpf,
    Character sexo,
    LocalDate dataNasc,
    String razaoSocial,
    String cnpj,

    String textoBio,
    List<ContatoResponse> contatos
) {}
