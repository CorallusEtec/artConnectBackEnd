package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDate;
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


    private Character sexo;
}
