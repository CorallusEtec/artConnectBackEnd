package corallus.artConnect.artConnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.publicacao.TipoReacao;

public interface TipoReacaoRepository extends JpaRepository<TipoReacao, Long> {
    Optional<TipoReacao> findByNomeTipo(String nomeTipo);
}
