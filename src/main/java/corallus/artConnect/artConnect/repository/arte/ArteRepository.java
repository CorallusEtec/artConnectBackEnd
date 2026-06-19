package corallus.artConnect.artConnect.repository.arte;

import corallus.artConnect.artConnect.dto.response.admin.ArteRelatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import corallus.artConnect.artConnect.entity.arte.Arte;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArteRepository extends JpaRepository<Arte, Long>, JpaSpecificationExecutor<Arte> {
    boolean existsByNomeArte(String nomeArte);

    @Query("SELECT new corallus.artConnect.artConnect.dto.response.admin.ArteRelatorio(count(art), a)" +
            " FROM Arte a JOIN a.artistas art " +
            "WHERE art.status.tipoStatus = ATIVO " +
            "GROUP BY a ORDER BY count(art) DESC")
    List<ArteRelatorio> arteRelatorio();
}
