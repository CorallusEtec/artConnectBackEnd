package corallus.artConnect.artConnect.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Estabelecimento extends Usuario{

	private Long id;
	private String razaoSocial;
	private String cnpj;
	
	public Estabelecimento(String email, String senha) {
		this.setEmail(email);
		this.setSenha(senha);
	}

	public void setSenha(String senha) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}
}
