package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class Artista extends Usuario {
    private String nomeArtistico;
    private LocalDate dataNasc;
    
    @ManyToOne
    private Arte arte;

    // CONSTRUTOR

    public Artista() {
    }

    public Artista(Long id, String nome, String email, String senha, String tipoConta, Status status,
            LocalDateTime dataCriacao, String nomeLog, Short numLog, String cep, String bairro, String complemento,
            String cidade, String uf, String textoBio, Set<Seguida> seguidores, Set<Seguida> seguido,
            List<Contato> contatos, List<Publicacao> publicacoes, String nomeArtistico, LocalDate dataNasc, Arte arte,
        Set<Reacao> reacoes) {
        super(id, nome, email, senha, tipoConta, status, dataCriacao, nomeLog, numLog, cep, bairro, complemento, cidade,
                uf, textoBio, seguidores, seguido, contatos, publicacoes, reacoes);
        this.nomeArtistico = nomeArtistico;
        this.dataNasc = dataNasc;
        this.arte = arte;
    }

    // GET E SET

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Arte getArte() {
        return arte;
    }

    public void setArte(Arte arte) {
        this.arte = arte;
    }

  

    
}
