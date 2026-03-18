package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "tb_status_conta")
@Table(name = "tb_status_conta")
public class StatusConta {
	public StatusConta(Long id, String nomeStatus, String descricao) {
		super();
		this.id = id;
		this.nomeStatus = nomeStatus;
		this.descricao = descricao;
	}
	public StatusConta() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, name = "nome_status")
	
	private String nomeStatus;
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeStatus() {
		return nomeStatus;
	}
	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
