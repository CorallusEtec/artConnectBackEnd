package corallus.artConnect.artConnect.repository.contato;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.contato.ContatoArtista;

public interface ContatoArtistaRepository extends JpaRepository<ContatoArtista, Long> {
    
}
