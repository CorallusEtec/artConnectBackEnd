package corallus.artConnect.artConnect.entity;

import java.time.LocalDateTime;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seguida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    private Usuario seguidor;

    @ManyToOne
    private Usuario seguido;
    
    private LocalDateTime dataSeguida = LocalDateTime.now();

    // CONSTRUTOR
    public Seguida() {

    }
    
    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Usuario seguidor) {
        this.seguidor = seguidor;
    }

    public Usuario getSeguido() {
        return seguido;
    }

    public void setSeguido(Usuario seguido) {
        this.seguido = seguido;
    }

    public LocalDateTime getDataSeguida() {
        return dataSeguida;
    }

    public void setDataSeguida(LocalDateTime dataSeguida) {
        this.dataSeguida = dataSeguida;
    }
}
