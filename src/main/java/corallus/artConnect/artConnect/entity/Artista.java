package corallus.artConnect.artConnect.entity;

import java.time.LocalDate;

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
@Table(name= "tb_artista")
@Entity(name = "tb_artista")
public class Artista extends Usuario {
	public Artista(String nome, String email, String senha, String tipoLog, String nomeLog, Integer numLog,
			String complemento, String cep, String tipoUsuario, String bairro, String cidade, String estado, Long id,
			LocalDate dataNasc, String cpf, Character sexo, Long idArte, String tipoUsuario2) {
		super(nome, email, senha, tipoLog, nomeLog, numLog, complemento, cep, tipoUsuario, bairro, cidade, estado);
		this.id = id;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
		this.sexo = sexo;
		this.idArte = idArte;
		tipoUsuario = tipoUsuario2;
	}
	public Artista() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataNasc;
	private String cpf;
	private Character sexo;

	private Long idArte;


	@Transient
	private String tipoUsuario = "ARTISTA";

	// GET E SET
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Character getSexo() {
		return sexo;
	}


	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}


	public Long getIdArte() {
		return idArte;
	}


	public void setIdArte(Long idArte) {
		this.idArte = idArte;
	}


	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}

