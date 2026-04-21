package corallus.artConnect.artConnect.repository.atores;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.atores.Contratante;

public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    boolean existsByCnpj(String cnpj);
    boolean existsByCpf(String cpf);
}
