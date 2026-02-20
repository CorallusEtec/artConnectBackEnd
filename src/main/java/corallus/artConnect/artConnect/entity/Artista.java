package corallus.artConnect.artConnect.entity;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artista extends Usuario{
	private LocalDate dataNasc;
	private String cpf;
	private char sexo;
	private List<Arte> tiposArte;
	/* 
	 * private List<File> publicacoes
	 * publicacoes será implementado mais tarde...
	 */
}
