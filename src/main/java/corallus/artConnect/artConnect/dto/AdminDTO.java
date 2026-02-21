package corallus.artConnect.artConnect.dto;

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
@Entity
@Table(name = "tbadmin")
public class AdminDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdin;
	private String nomeAdmin;
	private String emailAdmin;
	private String senhaAdmin;
	private String tipoLogra;
	private String nomeLogra;
	private Integer numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
}
