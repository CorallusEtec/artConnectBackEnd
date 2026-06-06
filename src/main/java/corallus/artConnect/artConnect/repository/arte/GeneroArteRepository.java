package corallus.artConnect.artConnect.repository.arte;

import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroArteRepository extends JpaRepository<GeneroArte, Long> {
    boolean existsByNomeGeneroArte(String nomeGeneroArte);
}