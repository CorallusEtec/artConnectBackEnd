package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.enumeration.ETipoConta;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.status.Status;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Dados Importantes
    private Long id;
    @JoinColumn(unique = true, nullable = false)
    private String nome;

    @JoinColumn(unique = true, nullable = false)
    private String email;
    @JoinColumn(nullable = false)
    private String senha;
    @Enumerated(EnumType.STRING)
    private ETipoConta tipoConta;
    @OneToOne
    private Status status;
    private LocalDateTime dataCriacao;
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
    
    // Metodos UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.tipoConta.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {

        return this.getEmail();
    }



    // CONSTRUTORES
    public Usuario() {}


    // GET E SET   


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public ETipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(ETipoConta tipoConta) {
        this.tipoConta = tipoConta;
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
}
