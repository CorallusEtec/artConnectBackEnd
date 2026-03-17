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
@Table(name= "tb_contato_estabelecimento")
@Entity(name = "tb_contato_estabelecimento")
public class ContatoEstabelecimento {
    public ContatoEstabelecimento(Long idContatoEstabelecimento, Long idTipoContato, String valorContatoEstabelecimento,
			Long idEstabelecimento) {
		super();
		this.idContatoEstabelecimento = idContatoEstabelecimento;
		this.idTipoContato = idTipoContato;
		this.valorContatoEstabelecimento = valorContatoEstabelecimento;
		this.idEstabelecimento = idEstabelecimento;
	}
    public ContatoEstabelecimento() {
    	
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContatoEstabelecimento;
	private Long idTipoContato;
	private String valorContatoEstabelecimento;
	private Long idEstabelecimento;
	
	
	public Long getIdContatoEstabelecimento() {
		return idContatoEstabelecimento;
	}
	public void setIdContatoEstabelecimento(Long idContatoEstabelecimento) {
		this.idContatoEstabelecimento = idContatoEstabelecimento;
	}
	public Long getIdTipoContato() {
		return idTipoContato;
	}
	public void setIdTipoContato(Long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}
	public String getValorContatoEstabelecimento() {
		return valorContatoEstabelecimento;
	}
	public void setValorContatoEstabelecimento(String valorContatoEstabelecimento) {
		this.valorContatoEstabelecimento = valorContatoEstabelecimento;
	}
	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
}
