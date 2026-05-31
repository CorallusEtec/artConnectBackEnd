package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
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
