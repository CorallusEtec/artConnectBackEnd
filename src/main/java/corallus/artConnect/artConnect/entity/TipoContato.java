package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
@Entity(name = "tb_tipo_contato")
@Table(name = "tb_tipo_contato")
public class TipoContato {

	public TipoContato(Long idTipoContato, String nomeTipoContato) {
		super();
		this.idTipoContato = idTipoContato;
		this.nomeTipoContato = nomeTipoContato;
	}
	public TipoContato() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContato;

    private String nomeTipoContato;

	public Long getIdTipoContato() {
		return idTipoContato;
	}

	public void setIdTipoContato(Long idTipoContato) {
		this.idTipoContato = idTipoContato;
	}

	public String getNomeTipoContato() {
		return nomeTipoContato;
	}

	public void setNomeTipoContato(String nomeTipoContato) {
		this.nomeTipoContato = nomeTipoContato;
	}
	
}
