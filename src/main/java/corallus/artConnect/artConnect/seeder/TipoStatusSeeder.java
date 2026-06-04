package corallus.artConnect.artConnect.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import corallus.artConnect.artConnect.entity.status.TipoStatus;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Component
public class TipoStatusSeeder implements CommandLineRunner{

    private final TipoStatusRepository tipoStatusRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoStatusSeeder(TipoStatusRepository tipoStatusRepository) {
        this.tipoStatusRepository = tipoStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // SE AINDA NÃO EXISTIR NO BANCO
        if(tipoStatusRepository.count() == 0) {
            // PARA CADA TIPO DE STATUS
            for(ETipoStatus t : ETipoStatus.values()) {
                // CRIA NO BANCO
                this.tipoStatusRepository.save(new TipoStatus(t));
            }
        }
    }
}
