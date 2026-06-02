package corallus.artConnect.artConnect.repository.status;

import java.util.Optional;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.status.TipoStatus;

public interface TipoStatusRepository extends JpaRepository<TipoStatus, Long>{
    Optional<TipoStatus> findByNomeTipoStatus(ETipoStatus nomeTipoStatus);
}
