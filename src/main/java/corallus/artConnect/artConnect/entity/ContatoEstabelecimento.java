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
    
	
	
    public ContatoEstabelecimento(Long id, String valorContato, Long idEstabelecimento) {
		super();
		this.id = id;
		this.valorContato = valorContato;
		this.idEstabelecimento = idEstabelecimento;
	}
	public ContatoEstabelecimento() {
    	
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String valorContato;
	private Long idEstabelecimento;
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
	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}
	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	
	
}
