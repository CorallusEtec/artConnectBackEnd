package corallus.artConnect.artConnect.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.repository.reacao.TipoReacaoRepository;

@Component
public class TipoReacaoSeeder implements CommandLineRunner {

    private final TipoReacaoRepository tipoReacaoRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoReacaoSeeder(TipoReacaoRepository tipoReacaoRepository) {
        this.tipoReacaoRepository = tipoReacaoRepository;
    }

    @Override
    public void run(String...args) throws Exception {
       if (tipoReacaoRepository.count() == 0) {
            for(ETipoReacao t : ETipoReacao.values()) {
                this.tipoReacaoRepository.save(new TipoReacao(t));
            }
       }
    }
}
