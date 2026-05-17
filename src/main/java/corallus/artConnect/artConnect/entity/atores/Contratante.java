package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

@Entity
public class Contratante extends Usuario {
    
    @JoinColumn(unique = true)
    private String cpf;
    private Character sexo;
    private LocalDate dataNasc;
    private String razaoSocial;
    @JoinColumn(unique = true)
    private String cnpj;


    // CONSTRUTOR

    public Contratante() {
    }


   

    // GET E SET

    public Contratante(Long id, String nome, String email, String senha, String tipoConta, Status status,
            LocalDateTime dataCriacao, String bio, String nomeLog, Short numLog, String cep, String bairro,
            String complemento, String cidade, String uf, String textoBio, Set<Seguida> seguidores,
            Set<Seguida> seguido, List<Contato> contatos, List<Publicacao> publicacoes, Set<Reacao> reacoes, String cpf,
            Character sexo, LocalDate dataNasc, String razaoSocial, String cnpj) {
        super(id, nome, email, senha, tipoConta, status, dataCriacao, bio, nomeLog, numLog, cep, bairro, complemento,
                cidade, uf, textoBio, seguidores, seguido, contatos, publicacoes, reacoes);
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
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


    public String getCnpj() {
        return cnpj;
    }


    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    

    

    
   
}
