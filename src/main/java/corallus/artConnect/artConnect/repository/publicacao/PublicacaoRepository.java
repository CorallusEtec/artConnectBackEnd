package corallus.artConnect.artConnect.repository.publicacao;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
    
}
