package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
