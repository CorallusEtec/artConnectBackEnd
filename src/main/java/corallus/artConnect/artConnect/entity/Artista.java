package corallus.artConnect.artConnect.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name= "tb_artista")
@Entity(name = "tb_artista")
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArtista;
	private String nomeArtista;
	private String emailArtista;
	private String senhaArtista;
	private String tipoLogArtista;
	private String nomeLogArtista;
	private Integer numLogArtista;
	private String complementoArtista;
	private String cepArtista;
	private String bairroArtista;
	private String cidadeArtista;
	private String estadoArtista;
	private LocalDate dataNascArtista;
	private String cpfArtista;
	private Character sexoArtista;

	@Transient // Essa coluna não será mapeada para o banco de dados, apenas para retorno para endpoints GET
	private List<TelefoneArtista> telefoneArtista;
}

