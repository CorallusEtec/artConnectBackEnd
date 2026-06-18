package corallus.artConnect.artConnect.repository.arte;

import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroArteRepository extends JpaRepository<GeneroArte, Long> {
    boolean existsByNomeGeneroArte(String nomeGeneroArte);

    List<GeneroArte> findAllByArte_Id(Long arteId);

    Long id(Long id);
}