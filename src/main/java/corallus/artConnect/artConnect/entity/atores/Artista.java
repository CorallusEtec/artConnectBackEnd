package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
import corallus.artConnect.artConnect.entity.arte.Arte;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@Entity
public class Artista extends Usuario {

    private String nomeArtistico;
    private LocalDate dataNasc;

    @ManyToOne
    private Arte arte;
    private Character sexo;
}
