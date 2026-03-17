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
    public ContatoAdmin(Long idContatoAdmin, Long idTipoContato, String valorContatoAdmin, Long idAdmin) {
		super();
		this.idContatoAdmin = idContatoAdmin;
		this.idTipoContato = idTipoContato;
		this.valorContatoAdmin = valorContatoAdmin;
		this.idAdmin = idAdmin;
	}
    public ContatoAdmin() {
    	
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContatoAdmin;
	private Long idTipoContato;
	private String valorContatoAdmin;
	private Long idAdmin;
	
	
	
	public Long getIdContatoAdmin() {
		return idContatoAdmin;
	}
	public void setIdContatoAdmin(Long idContatoAdmin) {
		this.idContatoAdmin = idContatoAdmin;
	}
	public Long getIdTipoContato() {
		return idTipoContato;
	}
	public void setIdTipoContato(Long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}
	public String getValorContatoAdmin() {
		return valorContatoAdmin;
	}
	public void setValorContatoAdmin(String valorContatoAdmin) {
		this.valorContatoAdmin = valorContatoAdmin;
	}
	public Long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
}
