package corallus.artConnect.artConnect.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


import corallus.artConnect.artConnect.entity.ContatoEstabelecimento;


public interface ContatoEstabelecimentoRepository extends JpaRepository<ContatoEstabelecimento, Long> {
    List<ContatoEstabelecimento> findByIdEstabelecimento(Long idEstabelecimento);
}
