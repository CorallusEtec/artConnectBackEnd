package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@Entity
@DiscriminatorValue("ARTISTA")
public class Artista extends Usuario {

    private String nomeArtistico;
    private LocalDate dataNasc;

    @ManyToOne
    private Arte arte;
    @ManyToMany
    @JoinTable(
            name = "artista_genero_arte",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_arte_id")
    )
    private List<GeneroArte> generosArte;

    private Character sexo;
}
