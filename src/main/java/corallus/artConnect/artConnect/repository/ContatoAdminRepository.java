package corallus.artConnect.artConnect.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.ContatoAdmin;

public interface ContatoAdminRepository extends JpaRepository<ContatoAdmin, Long> {
    List<ContatoAdmin> findByIdAdmin(Long idAdmin);
}
