package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Tag;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Artista extends Usuario {
    private String nomeArtistico;
    private LocalDate dataNasc;
    
    @JoinTable(
        name = "artista_tag",
        joinColumns = @JoinColumn(name = "artista_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ManyToMany
    private List<Tag> listaTags;

    @ManyToOne
    
    private Arte arte;

    // CONSTRUTOR

    public Artista() {
        super();
    }

   public Artista(Long id, String nome, String email, String senha, String tipoConta, Status status,
            LocalDateTime dataCriacao, String nomeLog, Short numLog, String cep, String bairro, String complemento,
            String cidade, String uf, String textoBio, Set<Seguida> seguidores, Set<Seguida> seguido,
            List<Contato> contatos, List<Publicacao> publicacoes, Set<Reacao> reacoes, String nomeArtistico,
            LocalDate dataNasc, List<Tag> listaTags, Arte arte) {
        super(id, nome, email, senha, tipoConta, status, dataCriacao, nomeLog, numLog, cep, bairro, complemento, cidade,
                uf, textoBio, seguidores, seguido, contatos, publicacoes, reacoes);
        this.nomeArtistico = nomeArtistico;
        this.dataNasc = dataNasc;
        this.listaTags = listaTags;
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

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) {
        this.listaTags = listaTags;
    }
    
}
