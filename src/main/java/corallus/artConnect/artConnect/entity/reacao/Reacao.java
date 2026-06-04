package corallus.artConnect.artConnect.entity.reacao;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "reacao")
public class Reacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private TipoReacao tipoReacao;
    private Boolean ativo;

    @JsonIgnoreProperties({"publicacoes", "reacoes"})
    @ManyToOne
    private Usuario usuario;

    @JsonIgnoreProperties({"usuario", "reacoes"})
    @ManyToOne
    private Comentario comentario;
    private LocalDateTime dataReacao = LocalDateTime.now();

    @ManyToOne
    private Publicacao publicacao;
}
