package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.publicacao.TipoReacao;

public interface TipoReacaoRepository extends JpaRepository<TipoReacao, Long> {
    
}
