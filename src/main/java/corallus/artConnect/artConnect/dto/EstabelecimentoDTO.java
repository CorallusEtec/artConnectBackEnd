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
// Nome da tabela igual no banco
@Table(name = "tbEstabelecimento")
@Entity
// Get Set e Construtores (Código boitherplate)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EstabelecimentoDTO {
	// Marcar a chave primária no banco
	@Id
	// Identity para criar os Id automaticamente
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
