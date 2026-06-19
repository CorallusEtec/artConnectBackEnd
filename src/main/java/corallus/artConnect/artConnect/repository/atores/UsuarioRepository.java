package corallus.artConnect.artConnect.repository.atores;

import java.util.Optional;

import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    Integer countUsuarioByStatus_TipoStatusAndTipoConta(ETipoStatus statusTipoStatus, ETipoConta tipoConta);
}
