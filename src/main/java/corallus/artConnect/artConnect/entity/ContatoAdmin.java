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
@Table(name= "tb_contato_admin")
@Entity(name = "tb_contato_admin")
public class ContatoAdmin {
	
	
    public ContatoAdmin(Long id, String valorContato, Long idAdmin) {
		super();
		this.id = id;
		this.valorContato = valorContato;
		this.idAdmin = idAdmin;
	}
	public ContatoAdmin() {
    	
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String valorContato;
	private Long idAdmin;
	
	
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
	public Long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	

}
