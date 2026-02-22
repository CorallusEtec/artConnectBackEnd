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
@Table(name = "tb_estabelecimento")
public class EstabelecimentoDTO {
	// Marcar a chave primária no banco
	@Id
	// Identity para criar os Id automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstabelecimento;
	private String nomeEsbelecimento;
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

	// Método para converter um objeto do tipo EstabelecimentoDTO para um objeto do tipo Estabelecimento
	public Estabelecimento toEntity() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome(this.nomeEsbelecimento);
		estabelecimento.setEmail(this.emailEstabelecimento);
		estabelecimento.setSenha(this.senhaEstabelecimento);
		estabelecimento.setCnpj(this.cnpjEstabelecimento);
		estabelecimento.setEndereco(Endereco.builder()
				.tipoLogra(this.tipoLogEstabelecimento)
				.nomeLogra(this.nomeLogEstabelecimento)
				.numLogra(this.numLogEstabelecimento)
				.complemento(this.complementoEstabelecimento)
				.cep(this.cepEstabelecimento)
				.bairro(this.bairroEstabelecimento)
				.cidade(this.cidadeEstabelecimento)
				.estado(this.estadoEstabelecimento)
				.build());
		estabelecimento.setRazaoSocial(this.razaoSocialEstabelecimento);
		estabelecimento.setId(this.idEstabelecimento);
		return estabelecimento;
	}

	// Método construtor que converte um objeto do tipo Estabelecimento para um objeto do tipo EstabelecimentoDTO
	public EstabelecimentoDTO(Estabelecimento pojo) {
		this.nomeEsbelecimento = pojo.getNome();
		this.emailEstabelecimento = pojo.getEmail();
		this.senhaEstabelecimento = pojo.getSenha();
		this.cnpjEstabelecimento = pojo.getCnpj();
		this.tipoLogEstabelecimento = pojo.getEndereco().getTipoLogra();
		this.nomeLogEstabelecimento = pojo.getEndereco().getNomeLogra();
		this.numLogEstabelecimento = pojo.getEndereco().getNumLogra();
		this.complementoEstabelecimento = pojo.getEndereco().getComplemento();
		this.cepEstabelecimento = pojo.getEndereco().getCep();
		this.bairroEstabelecimento = pojo.getEndereco().getBairro();
		this.cidadeEstabelecimento = pojo.getEndereco().getCidade();
		this.estadoEstabelecimento = pojo.getEndereco().getEstado();
		this.razaoSocialEstabelecimento = pojo.getRazaoSocial();
	}
}
