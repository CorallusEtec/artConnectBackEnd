package corallus.artConnect.artConnect.repository.atores;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Artista;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtistaRepository extends JpaRepository<Artista, Long>, JpaSpecificationExecutor<Artista> {
    
}
