package corallus.artConnect.artConnect.dto.response.artista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.enumeration.ETipoConta;

public record ArtistaResponse(
    // USUARIO
    Long id,
    String nome,
    String email,
    ETipoConta tipoConta,
    Status status,
    LocalDateTime dataCriacao,
    
    // ARTISTA
    String nomeArtistico,
    Arte arte,
    LocalDate dataNasc,
    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,

    String textoBio,
    Set<Seguida> seguidores,
    Set<Seguida> seguido,
    List<ContatoResponse> contatos
    
    /*
     * 
     * SEM PUBLICACOES E REACOES POR MOTIVOS DE ABSTRAÇÃO
     * 
     */
) { }

