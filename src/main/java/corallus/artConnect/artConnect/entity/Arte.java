package corallus.artConnect.artConnect.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arte {
	private String nome;
	private String descricao;
	public Arte(String nome, String desc) {
		this.nome = nome;
		this.descricao = desc;
	}
}
