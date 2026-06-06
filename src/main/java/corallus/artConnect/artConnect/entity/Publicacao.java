package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoMidia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Status statusPublicacao;

    @ManyToOne
    @JsonIgnoreProperties({"publicacoes", "reacoes", "comentarios"})
    private Usuario autor;
    private LocalDateTime dataPublicacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private ETipoMidia tipoMidia;

    @OneToMany(mappedBy = "publicacao")
    private Set<Reacao> reacoes;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios;
    private String legenda;
    private String urlMidia;
}
