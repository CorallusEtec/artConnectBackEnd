package corallus.artConnect.artConnect.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table(name= "tb_contato_artista")
@Entity(name = "tb_contato_artista")
public class ContatoArtista {
	public ContatoArtista(Long idContatoArtista, Long idTipoContato, String valorContatoArtista, Long idArtista) {
		super();
		this.idContatoArtista = idContatoArtista;
		this.idTipoContato = idTipoContato;
		this.valorContatoArtista = valorContatoArtista;
		this.idArtista = idArtista;
	}
	public ContatoArtista() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContatoArtista;
	private Long idTipoContato;
	private String valorContatoArtista;
	private Long idArtista;
	
	public Long getIdContatoArtista() {
		return idContatoArtista;
	}
	public void setIdContatoArtista(Long idContatoArtista) {
		this.idContatoArtista = idContatoArtista;
	}
	public Long getIdTipoContato() {
		return idTipoContato;
	}
	public void setIdTipoContato(Long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}
	public String getValorContatoArtista() {
		return valorContatoArtista;
	}
	public void setValorContatoArtista(String valorContatoArtista) {
		this.valorContatoArtista = valorContatoArtista;
	}
	public Long getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(Long idArtista) {
		this.idArtista = idArtista;
	}
}
