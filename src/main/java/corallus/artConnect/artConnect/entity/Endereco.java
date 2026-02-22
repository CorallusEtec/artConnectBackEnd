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
	private Integer numLogra;
	private String complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	
}
