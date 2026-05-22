package corallus.artConnect.artConnect.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import corallus.artConnect.artConnect.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
	List<Publicacao> findByStatusPublicacao_TipoStatus_IdOrderByDataPublicacaoDesc(Long id);

	@Query(value = 
	"SELECT p.* FROM publicacao p "+
	"INNER JOIN status ON status.id = p.status_publicacao_id "+
	"INNER JOIN tipo_status ON tipo_status.id = status.tipo_status_id "+
	"INNER JOIN usuario ON p.autor_id = usuario.id "+
	"INNER JOIN artista ON usuario.id = artista.id "+
	"INNER JOIN arte ON arte.id = artista.arte_id "+
	"WHERE arte.nome_arte LIKE CONCAT('%', :nomeArte, '%') AND tipo_status.nome_tipo_status = 'ATIVO'", nativeQuery = true
	)
	List<Publicacao> findAllPublicacoesByNomeArteOrderByData(String nomeArte, Sort sort);

	@Query(value = 
	"SELECT p.* FROM publicacao p "+
	"INNER JOIN status ON status.id = p.status_publicacao_id "+
	"INNER JOIN tipo_status ON tipo_status.id = status.tipo_status_id "+
	"WHERE tipo_status.nome_tipo_status = 'ATIVO'", nativeQuery = true
	)
	List<Publicacao> findAllPublicacoesOrderByData(Sort sort);


}
