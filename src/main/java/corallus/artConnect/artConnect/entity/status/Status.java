package corallus.artConnect.artConnect.entity.status;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private TipoStatus tipoStatus;

    private String descricao;
    private LocalDateTime dataModificacao;
}
