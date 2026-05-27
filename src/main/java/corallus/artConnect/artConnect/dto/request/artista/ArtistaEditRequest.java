package corallus.artConnect.artConnect.dto.request.artista;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.Tag;

public record ArtistaEditRequest(
    // USUARIO
    String nome,
    String textoBio,

    // ARTISTA
    List<Tag> listaTags,
    Arte arte,
    String nomeArtistico,
    LocalDate dataNasc,

    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf

) {}
