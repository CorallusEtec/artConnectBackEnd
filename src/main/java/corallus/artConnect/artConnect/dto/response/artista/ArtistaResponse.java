package corallus.artConnect.artConnect.dto.response.artista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoDetailsResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
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
    LocalDate dataNasc,
    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,

    List<PublicacaoDetailsResponse> publicacoes,
    String textoBio,
    List<ContatoResponse> contatos,
    Arte arte,
    List<GeneroArte> generosArte

) { }

