package corallus.artConnect.artConnect.entity;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter @Getter
public class Endereco {
	private String tipoLogra;
	private String nomeLogra;
	private int numLogra;
	private Optional<String> complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	
}
