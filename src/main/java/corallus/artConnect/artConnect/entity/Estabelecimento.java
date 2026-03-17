package corallus.artConnect.artConnect.entity;

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
	public Estabelecimento(String nome, String email, String senha, String tipoLog, String nomeLog, Integer numLog,
			String complemento, String cep, String tipoUsuario, String bairro, String cidade, String estado, Long id,
			String cnpj, String razaoSocial, String tipoUsuario2) {
		super(nome, email, senha, tipoLog, nomeLog, numLog, complemento, cep, tipoUsuario, bairro, cidade, estado);
		this.id = id;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		tipoUsuario = tipoUsuario2;
	}
	public Estabelecimento() {
		
	}

	// Marcar a chave primária no banco
	@Id
	// Identity para criar os Id automaticamente
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cnpj;
	private String razaoSocial;

	@Transient
	private String tipoUsuario = "ESTABELECIMENTO";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
