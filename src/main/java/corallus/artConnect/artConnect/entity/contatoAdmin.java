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

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name= "tb_contato_admin")
@Entity(name = "tb_contato_admin")
public class ContatoAdmin {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContatoAdmin;
	private Long idTipoContato;
	private String valorContatoAdmin;
	private Long idAdmin;
}
