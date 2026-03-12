package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.ContatoArtista;
import java.util.List;


public interface ContatoArtistaRepository extends JpaRepository<ContatoArtista, Long> {
    List<ContatoArtista> findByIdArtista(Long idArtista);
}
