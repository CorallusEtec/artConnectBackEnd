package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.entity.atores.Usuario;
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
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Status statusComentario;
    private LocalDateTime dataComentario;
    private String mensagem;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    private Publicacao publicacao;
    
    @OneToMany(mappedBy = "comentario")
    private Set<Reacao> reacoes;
}
