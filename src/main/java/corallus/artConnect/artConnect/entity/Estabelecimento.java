package corallus.artConnect.artConnect.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Estabelecimento extends Usuario{

	private Long id;
	private String razaoScial;
	private String cnpj;
	
	public Estabelecimento(String email, String senha) {
		this.setEmail(email);
		this.setSenha(senha);
	}

	private void setSenha(String senha) {
		// TODO Auto-generated method stub
		
	}

	private void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}
}
