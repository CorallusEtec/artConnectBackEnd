package corallus.artConnect.artConnect.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.dto.ArtistaDTO;

public interface ArtistaRepository extends JpaRepository<ArtistaDTO, Long> {
    
}
