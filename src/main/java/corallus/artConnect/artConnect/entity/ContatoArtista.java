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
	
	public ContatoArtista() {
		
	}
	public ContatoArtista(Long id, String valorContato, Long idArtista) {
		super();
		this.id = id;
		this.valorContato = valorContato;
		this.idArtista = idArtista;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String valorContato;
	private Long idArtista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValorContato() {
		return valorContato;
	}
	public void setValorContato(String valorContato) {
		this.valorContato = valorContato;
	}
	public Long getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(Long idArtista) {
		this.idArtista = idArtista;
	}
	
}
