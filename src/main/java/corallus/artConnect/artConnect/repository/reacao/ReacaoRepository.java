package corallus.artConnect.artConnect.repository.reacao;

import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.reacao.Reacao;

import java.util.Optional;

public interface ReacaoRepository extends JpaRepository<Reacao, Long> {
    Optional<Reacao> findByUsuario_IdAndPublicacao_Id(Long usuarioId, Long publicacaoId);

    Optional<Reacao> findByUsuario_IdAndComentario_Id(Long usuarioId, Long comentarioId);
}
