package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Column;
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
@NoArgsConstructor @AllArgsConstructor
@Entity(name = "tb_arte")
@Table(name = "tb_arte")
public class Arte {
    public Arte(Long id, String nomeArte, String descricaoArte) {
		super();
		this.id = id;
		this.nomeArte = nomeArte;
		this.descricaoArte = descricaoArte;
	}
    public Arte() {
    	
    }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeArte;
    
    @Column(nullable = true)
    private String descricaoArte;

    
    
    // GET E SET
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArte() {
		return nomeArte;
	}

	public void setNomeArte(String nomeArte) {
		this.nomeArte = nomeArte;
	}

	public String getDescricaoArte() {
		return descricaoArte;
	}

	public void setDescricaoArte(String descricaoArte) {
		this.descricaoArte = descricaoArte;
	}
}
