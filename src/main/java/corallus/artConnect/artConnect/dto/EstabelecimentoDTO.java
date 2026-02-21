package corallus.artConnect.artConnect.dto;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tbEstabelecimento")
public class EstabelecimentoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstabelecimento;
	private String nomeEstabelecimento;
	private String emailEstabelecimento;
	private String senhaEstabelecimento;
	private String cnpjEstabelecimento;
	private String tipoLogEstabelecimento;
	private String nomeLogEstabelecimento;
	private int numLogEstabelecimento;
	private Optional<String> complemento;
	private String cepEstabelecimento;
	private String bairroEstabelecimento;
	private String cidadeEstabelecimento;
	private String estadoEstabelecimento;
	private String razaoSocialEstabelecimento;
}
