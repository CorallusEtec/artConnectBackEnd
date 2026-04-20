package corallus.artConnect.artConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.TipoStatus;

public interface TipoStatusRepository extends JpaRepository<TipoStatus, Long>{
    Optional<TipoStatus> findByNomeTipoStatus(String nomeTipoStatus);
}
