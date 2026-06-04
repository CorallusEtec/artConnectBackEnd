package corallus.artConnect.artConnect.entity.reacao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
public class TipoReacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoReacao nomeTipo;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoReacao")
    private List<Reacao> reacoes;

    // PARA SEEDERS
    public TipoReacao(ETipoReacao nomeTipo) {
        this.setId(null);
        this.setReacoes(new ArrayList<>());
        this.setNomeTipo(nomeTipo);
    }
}
