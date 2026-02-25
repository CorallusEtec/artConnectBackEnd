package corallus.artConnect.artConnect.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    
}
