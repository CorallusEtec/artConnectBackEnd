package corallus.artConnect.artConnect.repository.status;

import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
