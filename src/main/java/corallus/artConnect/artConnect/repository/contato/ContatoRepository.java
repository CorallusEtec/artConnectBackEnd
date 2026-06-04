package corallus.artConnect.artConnect.repository.contato;

import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.contato.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}
