package corallus.artConnect.artConnect.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.atores.Usuario;

public interface SeguidaRepository extends JpaRepository<Seguida, Long> {
    
    boolean existsBySeguidorAndSeguido(Usuario seguidor, Usuario seguido);
    Optional<Seguida> findBySeguidorAndSeguido(Usuario seguidor, Usuario seguido);
}