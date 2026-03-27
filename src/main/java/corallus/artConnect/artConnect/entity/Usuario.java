package corallus.artConnect.artConnect.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String tipoConta;
    
    // LOGRADOURO / ENDEREÇO
    private String nomeLog;
    private Short numLog;
    private String cep;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
    

    // CONSTRUTORES
    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, String tipoConta, String nomeLog, Short numLog,
            String cep, String bairro, String complemento, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoConta = tipoConta;
        this.nomeLog = nomeLog;
        this.numLog = numLog;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
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
}
