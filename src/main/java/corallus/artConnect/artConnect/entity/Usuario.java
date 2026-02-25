package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
	private String bairro;
	private String cidade;
	private String estado;
}
