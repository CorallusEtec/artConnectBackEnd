package corallus.artConnect.artConnect.repository;

import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import corallus.artConnect.artConnect.entity.Publicacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>, JpaSpecificationExecutor<Publicacao> {
    Integer countAllByStatusPublicacao_TipoStatus(ETipoStatus statusPublicacaoTipoStatus);

    @Query("SELECT FUNCTION('DATE', p.dataPublicacao), COUNT(p) " +
            "FROM Publicacao p " +
            "WHERE p.dataPublicacao BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY FUNCTION('DATE', p.dataPublicacao) " +
            "ORDER BY FUNCTION('DATE', p.dataPublicacao) ASC ")
    List<Object[]> relatorioPublicacoes(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim
            );
}

