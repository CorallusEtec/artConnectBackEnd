package corallus.artConnect.artConnect.repository.atores;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
