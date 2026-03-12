package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public abstract class Usuario {
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
}
