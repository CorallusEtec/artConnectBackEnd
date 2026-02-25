package corallus.artConnect.artConnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "tb_tel_artista")
public class TelefoneArtista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTelArtista;
    private String numTelArtista;
    
    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Long idArtista;
}
