package corallus.artConnect.artConnect.entity;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.contato.ContatoContratante;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Contratante extends Usuario {
    
    private String statusConta;
    private String cpf;
    private String cnpj;
    private LocalDate dataNasc;
    private String razaoSocial;

    @OneToMany(mappedBy = "contratante")
    private List<ContatoContratante> contatos;

    @OneToMany(mappedBy = "contratante")
    private List<Reacao> reacoes;

    // CONSTRUTOR

    public Contratante() {
    }

    public Contratante(Long id, String nome, String email, String senha, String tipoConta, String nomeLog, Short numLog,
            String cep, String bairro, String complemento, String cidade, String uf, String statusConta, String cpf,
            String cnpj, LocalDate dataNasc, String razaoSocial, List<ContatoContratante> contatos,
            List<Reacao> reacoes) {
        super(id, nome, email, senha, tipoConta, nomeLog, numLog, cep, bairro, complemento, cidade, uf);
        this.statusConta = statusConta;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.dataNasc = dataNasc;
        this.razaoSocial = razaoSocial;
        this.contatos = contatos;
        this.reacoes = reacoes;
    }

    // GET E SET

    public String getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<ContatoContratante> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoContratante> contatos) {
        this.contatos = contatos;
    }

    public List<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(List<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

    
}
