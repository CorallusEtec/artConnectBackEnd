package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
@Entity(name = "tb_tipo_contato")
@Table(name = "tb_tipo_contato")
public class tipoContato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContato;

    private String nomeTipoContato;
	
}
