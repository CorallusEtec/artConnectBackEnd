package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToMany(mappedBy = "publicacao")
    private Set<Reacao> reacoes;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios;
    
    private String legenda;
    private String urlMidia;
}
