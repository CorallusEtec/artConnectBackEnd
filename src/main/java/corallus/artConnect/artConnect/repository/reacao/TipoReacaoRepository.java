package corallus.artConnect.artConnect.repository.reacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

public interface TipoReacaoRepository extends JpaRepository<TipoReacao, Long> {
    Optional<TipoReacao> findByNomeTipo(String nomeTipo);
}
