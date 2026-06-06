package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ETipoReacao tipoReacao;
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
