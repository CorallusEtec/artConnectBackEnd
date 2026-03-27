package corallus.artConnect.artConnect.repository.contato;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.contato.TipoContato;

public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {
    
}
