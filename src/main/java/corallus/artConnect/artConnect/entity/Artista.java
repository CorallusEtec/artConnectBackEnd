package corallus.artConnect.artConnect.entity;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.contato.ContatoArtista;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Artista extends Usuario {
    private String nomeArtistico;
    private LocalDate dataNasc;
    private String cpf;
    private Character sexo;
    private String statusConta;
    
    @ManyToOne
    private Arte arte;

    @OneToMany(mappedBy = "artista")
    private List<ContatoArtista> contatos;
    
    
    @OneToMany(mappedBy = "autor")
    private List<Publicacao> publicacoes;

    @OneToMany(mappedBy = "artista")
    private List<Reacao> reacoes;

    // CONSTRUTOR

    public Artista() {
    }

    public Artista(Long id, String nome, String email, String senha, String tipoConta, String nomeLog, Short numLog,
            String cep, String bairro, String complemento, String cidade, String uf, String nomeArtistico,
            LocalDate dataNasc, String cpf, Character sexo, String statusConta, Arte arte,
            List<ContatoArtista> contatos, List<Publicacao> publicacoes, List<Reacao> reacoes) {
        super(id, nome, email, senha, tipoConta, nomeLog, numLog, cep, bairro, complemento, cidade, uf);
        this.nomeArtistico = nomeArtistico;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.sexo = sexo;
        this.statusConta = statusConta;
        this.arte = arte;
        this.contatos = contatos;
        this.publicacoes = publicacoes;
        this.reacoes = reacoes;
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


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public Character getSexo() {
        return sexo;
    }


    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }


    public String getStatusConta() {
        return statusConta;
    }


    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }


    public Arte getArte() {
        return arte;
    }


    public void setArte(Arte arte) {
        this.arte = arte;
    }


    public List<ContatoArtista> getContatos() {
        return contatos;
    }


    public void setContatos(List<ContatoArtista> contatos) {
        this.contatos = contatos;
    }


    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }


    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }


    public List<Reacao> getReacoes() {
        return reacoes;
    }


    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    
}
