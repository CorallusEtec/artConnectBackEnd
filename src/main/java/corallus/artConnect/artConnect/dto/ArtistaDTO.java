package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name= "tbartista")
public class ArtistaDTO {
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

}
