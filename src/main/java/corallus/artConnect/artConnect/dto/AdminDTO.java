package corallus.artConnect.artConnect.dto;
import corallus.artConnect.artConnect.entity.Endereco;
import corallus.artConnect.artConnect.entity.Admin;
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
@Table(name = "tb_admin")
public class AdminDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdmin;
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
	
	// Método construtor para converter um Admin em AdminDTO
	public AdminDTO(Admin pojo) {
		this.nomeAdmin = pojo.getNome();
		this.emailAdmin = pojo.getEmail();
		this.senhaAdmin = pojo.getSenha();
		this.tipoLogra = pojo.getEndereco().getTipoLogra();
		this.nomeLogra = pojo.getEndereco().getNomeLogra();
		this.numero = pojo.getEndereco().getNumLogra();
		this.complemento = pojo.getEndereco().getComplemento();
		this.cep = pojo.getEndereco().getCep();
		this.bairro = pojo.getEndereco().getBairro();
		this.cidade = pojo.getEndereco().getCidade();
		this.estado = pojo.getEndereco().getEstado();
	}

	// Método para converter um AdminDTO em Admin
	public Admin toEntity() {
		Admin admin = new Admin();
		admin.setNome(this.nomeAdmin);
		admin.setEmail(this.emailAdmin);
		admin.setSenha(this.senhaAdmin);
		admin.setEndereco(Endereco.builder()
				.tipoLogra(this.tipoLogra)
				.nomeLogra(this.nomeLogra)
				.numLogra(this.numero)
				.complemento(this.complemento)
				.cep(this.cep)
				.bairro(this.bairro)
				.cidade(this.cidade)
				.estado(this.estado)
				.build());
		admin.setId(this.idAdmin);
		return admin;
	}
}
