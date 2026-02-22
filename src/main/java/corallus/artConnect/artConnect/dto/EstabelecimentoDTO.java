package corallus.artConnect.artConnect.dto;

import corallus.artConnect.artConnect.entity.Estabelecimento;
import corallus.artConnect.artConnect.entity.Endereco;
import jakarta.persistence.Column;
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
// Get Set e Construtores (Código boitherplate)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "tbestabelecimento")
public class EstabelecimentoDTO {
	// Marcar a chave primária no banco
	@Id
	// Identity para criar os Id automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestabelecimento")
	private Long id;
	@Column(name = "nomeestabelecimento")
	private String nome;
	@Column(name = "emailestabelecimento")
	private String email;
	@Column(name = "senhaestabelecimento")
	private String senha;
	@Column(name = "cnpjestabelecimento")
	private String cnpj;
	@Column(name = "tipologestabelecimento")
	private String tipoLog;
	@Column(name = "nomelogestabelecimento")
	private String nomeLog;
	@Column(name = "numlogestabelecimento")
	private Integer numLog;
	@Column(name = "complementoestabelecimento")
	private String complemento;
	@Column(name = "cepestabelecimento")
	private String cep;
	@Column(name = "bairroestabelecimento")
	private String bairro;
	@Column(name = "cidadeestabelecimento")
	private String cidade;
	@Column(name = "estadoestabelecimento")
	private String estado;
	@Column(name = "razaosocialestabelecimento")
	private String razaoSocial;

	// Método construtor que converte um objeto do tipo Estabelecimento para um objeto do tipo EstabelecimentoDTO
	public EstabelecimentoDTO(Estabelecimento pojo) {
		this.nome = pojo.getNome();
		this.email = pojo.getEmail();
		this.senha = pojo.getSenha();
		this.cnpj = pojo.getCnpj();
		this.tipoLog = pojo.getEndereco().getTipoLogra();
		this.nomeLog = pojo.getEndereco().getNomeLogra();
		this.numLog = pojo.getEndereco().getNumLogra();
		this.complemento = pojo.getEndereco().getComplemento();
		this.cep = pojo.getEndereco().getCep();
		this.bairro = pojo.getEndereco().getBairro();
		this.cidade = pojo.getEndereco().getCidade();
		this.estado = pojo.getEndereco().getEstado();
		this.razaoSocial = pojo.getRazaoSocial();
	}
}
