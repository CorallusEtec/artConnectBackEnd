package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("CONTRATANTE")
public class Contratante extends Usuario {
    
    @Column(unique = true)
    private String cpf;
    private Character sexo;
    private LocalDate dataNasc;
    private String razaoSocial;
    @Column(unique = true)
    private String cnpj;
}
