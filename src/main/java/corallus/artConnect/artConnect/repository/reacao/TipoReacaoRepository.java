package corallus.artConnect.artConnect.repository.reacao;

import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;
import java.util.Optional;

public interface TipoReacaoRepository extends JpaRepository<TipoReacao, Long> {
    Optional<TipoReacao> findByNomeTipo(ETipoReacao nomeTipo);
}
