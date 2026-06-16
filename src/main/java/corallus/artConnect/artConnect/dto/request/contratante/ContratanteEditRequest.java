package corallus.artConnect.artConnect.dto.request.contratante;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import jakarta.validation.constraints.NotBlank;

public record ContratanteEditRequest(
    // USUARIO

    @NotBlank(message = "O nome não pode ser vazio")
    String nome,
    String textoBio,

    // CONTRATANTE
    LocalDate dataNasc,
    String razaoSocial,
    Character sexo,

    List<GeneroArte> generosArte,
    Arte arte,
    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf
) implements CommonEdit {}
