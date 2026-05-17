package corallus.artConnect.artConnect.dto.atores.artista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.Tag;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.contato.Contato;

public record ArtistaDTO(
    // USUARIO
    Long id,
    String nome,
    String email,
    String tipoConta,
    Status status,
    LocalDateTime dataCriacao,
    
    // ARTISTA
    String nomeArtistico,
    List<Tag> listaTags,
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
    List<Contato> contatos
    
    /**
     * 
     * SEM PUBLICACOES E REACOES POR MOTIVOS DE ABSTRAÇÃO
     * 
     */
) {
    public static ArtistaDTO toDTO(Artista m) {
        ArtistaDTO dto = new ArtistaDTO(m.getId(), m.getNome(), m.getEmail(),
        m.getTipoConta(), m.getStatus(), m.getDataCriacao(), m.getNomeArtistico(),
        m.getListaTags(),
        m.getArte(), m.getDataNasc(), m.getNomeLog(), m.getNumLog(), m.getCep(),
        m.getBairro(), m.getComplemento(), m.getCidade(), m.getUf(), m.getTextoBio(),
        m.getSeguidores(), m.getSeguido(), m.getContatos());
        return dto;
    }
}
