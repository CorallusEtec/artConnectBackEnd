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
public class Estabelecimento extends Usuario {
	// Marcar a chave primária no banco
	@Id
	// Identity para criar os Id automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cnpj;
	private String razaoSocial;

	@Transient // Essa coluna não será mapeada para o banco de dados, apenas para retorno para endpoints GET
	private List<TelefoneEstabelecimento> telefone;
}
