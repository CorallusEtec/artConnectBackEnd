package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.entity.reacao.TipoReacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.repository.reacao.TipoReacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoReacaoService {
    private final TipoReacaoRepository tipoReacaoRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoReacaoService(TipoReacaoRepository tipoReacaoRepository) {
        this.tipoReacaoRepository = tipoReacaoRepository;
    }

    public TipoReacao getTipoReacao(ETipoReacao tipoReacao) {
        return tipoReacaoRepository.findByNomeTipo(tipoReacao)
                .orElseThrow(()->new IllegalArgumentException("Tipo de reação inválida"));

    }
}
