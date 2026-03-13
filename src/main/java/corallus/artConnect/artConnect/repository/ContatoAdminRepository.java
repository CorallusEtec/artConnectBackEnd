package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.ContatoEstabelecimento;

public interface ContatoAdminRepository extends JpaRepository<ContatoEstabelecimento, Long> {
    
}
