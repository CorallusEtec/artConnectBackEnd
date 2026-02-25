package corallus.artConnect.artConnect.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity(name = "tb_estabelecimento")
@Table(name = "tb_estabelecimento")
public class Estabelecimento {
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
	private Integer numLogEstabelecimento;
	private String complementoEstabelecimento;
	private String cepEstabelecimento;
	private String bairroEstabelecimento;
	private String cidadeEstabelecimento;
	private String estadoEstabelecimento;
	private String razaoSocialEstabelecimento;

	@Transient // Essa coluna não será mapeada para o banco de dados, apenas para retorno para endpoints GET
	private List<TelefoneEstabelecimento> telefoneEstabelecimento;
}
