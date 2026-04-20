package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.contato.Contato;

public record ArtistaEditDTO(
    // USUARIO
    String nome,
    String textoBio,
    List<Contato> contatos,

    // ARTISTA
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
