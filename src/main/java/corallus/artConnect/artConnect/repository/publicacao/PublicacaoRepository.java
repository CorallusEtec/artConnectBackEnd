package corallus.artConnect.artConnect.repository.publicacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
	List<Publicacao> findByStatusPublicacao_TipoStatus_IdOrderByDataPublicacaoDesc(Long id);
}
