package corallus.artConnect.artConnect.repository.reacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.reacao.Reacao;

public interface ReacaoRepository extends JpaRepository<Reacao, Long> {
    Optional<Reacao> findByUsuario_IdAndPublicacao_Id(Long usuarioId, Long PublicacaoId);
    
    Optional<Reacao> findByUsuario_IdAndComentario_Id(Long usuarioId, Long ComentarioId);
}
