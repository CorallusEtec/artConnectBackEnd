package corallus.artConnect.artConnect.repository.arte;

import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.arte.Arte;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArteRepository extends JpaRepository<Arte, Long>, JpaSpecificationExecutor<Arte> {
    boolean existsByNomeArte(String nomeArte);
}
