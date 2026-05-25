package corallus.artConnect.artConnect.repository.atores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
