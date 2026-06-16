package corallus.artConnect.artConnect.entity.contato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    private String valorContato;

    @ManyToOne
    private TipoContato tipoContato;
}
