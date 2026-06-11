package corallus.artConnect.artConnect.dto.request.artista;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import jakarta.validation.constraints.NotBlank;
public record ArtistaEditRequest(
    // USUARIO

    @NotBlank(message = "O nome não pode ser vazio")
    String nome,
    String textoBio,

    // ARTISTA
    Arte arte,
    String nomeArtistico,
    LocalDate dataNasc,
    List<GeneroArte> generosArte,
    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf

) implements CommonEdit {}
