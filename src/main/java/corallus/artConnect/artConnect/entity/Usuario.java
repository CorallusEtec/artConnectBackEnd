package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public abstract class Usuario {
    public Usuario(String nome, String email, String senha, String tipoLog, String nomeLog, Integer numLog,
			String complemento, String cep, String tipoUsuario, String bairro, String cidade, String estado) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoLog = tipoLog;
		this.nomeLog = nomeLog;
		this.numLog = numLog;
		this.complemento = complemento;
		this.cep = cep;
		this.tipoUsuario = tipoUsuario;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
    public Usuario() {
    	
    }
	@Column(nullable = false)
    private String nome;
    @Column(nullable = false)
	private String email;
    @Column(nullable = false)
	private String senha;
	private String tipoLog;
	private String nomeLog;
	private Integer numLog;
	private String complemento;
	private String cep;
	@Transient
	private String tipoUsuario;
	private String bairro;
	private String cidade;
	private String estado;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipoLog() {
		return tipoLog;
	}
	public void setTipoLog(String tipoLog) {
		this.tipoLog = tipoLog;
	}
	public String getNomeLog() {
		return nomeLog;
	}
	public void setNomeLog(String nomeLog) {
		this.nomeLog = nomeLog;
	}
	public Integer getNumLog() {
		return numLog;
	}
	public void setNumLog(Integer numLog) {
		this.numLog = numLog;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
