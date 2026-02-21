package corallus.artConnect.artConnect.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class Usuario {
	private String nome;
	private String email;
	private String senha;
	private List<String> telefone;
	private Endereco endereco;
}
