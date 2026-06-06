package corallus.artConnect.artConnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.Comentario;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>, JpaSpecificationExecutor<Comentario> {
    Page<Comentario> findAllByPublicacao_Id(Long publicacaoId, Pageable pageable, Specification<Comentario> specification);
}
