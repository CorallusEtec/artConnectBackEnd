package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Dados Importantes
    private Long id;
    private String nome;

    @JoinColumn(unique = true)
    private String email;
    private String senha;
    private String tipoConta;
    @OneToOne
    private Status status;
    private LocalDateTime dataCriacao;
    private String bio;
    // LOGRADOURO / ENDEREÇO
    private String nomeLog;
    private Short numLog;
    private String cep;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
    

    // Outros atributos
    private String textoBio;

    @OneToMany(mappedBy = "seguidor")
    private Set<Seguida> seguidores;
    @OneToMany(mappedBy = "seguido")
    private Set<Seguida> seguido;
    @OneToMany(mappedBy = "usuario")
    private List<Contato> contatos;
    @OneToMany(mappedBy = "autor")
    private List<Publicacao> publicacoes;
    @OneToMany(mappedBy = "usuario")
    private Set<Reacao> reacoes;
    // CONSTRUTORES
    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, String tipoConta, Status status,
            LocalDateTime dataCriacao, String bio, String nomeLog, Short numLog, String cep, String bairro,
            String complemento, String cidade, String uf, String textoBio, Set<Seguida> seguidores,
            Set<Seguida> seguido, List<Contato> contatos, List<Publicacao> publicacoes, Set<Reacao> reacoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoConta = tipoConta;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.bio = bio;
        this.nomeLog = nomeLog;
        this.numLog = numLog;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
        this.textoBio = textoBio;
        this.seguidores = seguidores;
        this.seguido = seguido;
        this.contatos = contatos;
        this.publicacoes = publicacoes;
        this.reacoes = reacoes;
    }

    

    // GET E SET   


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeLog() {
        return nomeLog;
    }

    public void setNomeLog(String nomeLog) {
        this.nomeLog = nomeLog;
    }

    public Short getNumLog() {
        return numLog;
    }

    public void setNumLog(Short numLog) {
        this.numLog = numLog;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTextoBio() {
        return textoBio;
    }

    public void setTextoBio(String textoBio) {
        this.textoBio = textoBio;
    }

    public Set<Seguida> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Set<Seguida> seguidores) {
        this.seguidores = seguidores;
    }

    public Set<Seguida> getSeguido() {
        return seguido;
    }

    public void setSeguido(Set<Seguida> seguido) {
        this.seguido = seguido;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public Set<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(Set<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
