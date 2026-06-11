package corallus.artConnect.artConnect.entity.arte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.entity.atores.Artista;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
public class GeneroArte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeGeneroArte;

    @JsonIgnore
    @ManyToMany
    private List<Artista> artistas;

    @ManyToOne
    private Arte arte;
}