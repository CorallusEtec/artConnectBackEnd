package corallus.artConnect.artConnect.entity.status;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
@Entity
public class TipoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoStatus nomeTipoStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoStatus")
    private List<Status> status;

    // PARA AS SEEDERS
    public TipoStatus(ETipoStatus nomeTipoStatus){
        this.setId(null);
        this.setNomeTipoStatus(nomeTipoStatus);
    }
}