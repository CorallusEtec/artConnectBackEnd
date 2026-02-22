package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;
import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.entity.Endereco;
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

	// Método construtor que converte um objeto do tipo Artista para um objeto do tipo ArtistaDTO
	public ArtistaDTO(Artista pojo) {
		this.nomeArtista = pojo.getNome();
		this.emailArtista = pojo.getEmail();
		this.senhaArtista = pojo.getSenha();
		this.tipoLogArtista = pojo.getEndereco().getTipoLogra();
		this.nomeLogArtista = pojo.getEndereco().getNomeLogra();
		this.numLogArtista = pojo.getEndereco().getNumLogra();
		this.complementoArtista = pojo.getEndereco().getComplemento();
		this.cepArtista = pojo.getEndereco().getCep();
		this.bairroArtista = pojo.getEndereco().getBairro();
		this.cidadeArtista = pojo.getEndereco().getCidade();
		this.estadoArtista = pojo.getEndereco().getEstado();
		this.dataNascArtista = pojo.getDataNasc();
		this.cpfArtista = pojo.getCpf();
		this.sexoArtista = pojo.getSexo();
	}
	
	public Artista toEntity() {
		Artista artista = new Artista();
		artista.setNome(this.nomeArtista);
		artista.setEmail(this.emailArtista);
		artista.setSenha(this.senhaArtista);
		artista.setDataNasc(this.dataNascArtista);
		artista.setCpf(this.cpfArtista);
		artista.setSexo(this.sexoArtista);
		artista.setEndereco(Endereco.builder()
				.tipoLogra(this.tipoLogArtista)
				.nomeLogra(this.nomeLogArtista)
				.numLogra(this.numLogArtista)
				.complemento(this.complementoArtista)
				.cep(this.cepArtista)
				.bairro(this.bairroArtista)
				.cidade(this.cidadeArtista)
				.estado(this.estadoArtista)
				.build());
		artista.setId(this.idArtista);
		//artista.setPublicacoes(null); // A lista de publicações será implementada posteriormente
		return artista;
	}
}
