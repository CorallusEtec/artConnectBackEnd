package corallus.artConnect.artConnect.repository.atores;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContratanteRepository extends JpaRepository<Contratante, Long>, JpaSpecificationExecutor<Contratante> {}
