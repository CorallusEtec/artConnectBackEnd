package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
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

    @Enumerated(EnumType.STRING)
    private ETipoStatus tipoStatus;

    private String descricao;
    private LocalDateTime dataModificacao;
}
