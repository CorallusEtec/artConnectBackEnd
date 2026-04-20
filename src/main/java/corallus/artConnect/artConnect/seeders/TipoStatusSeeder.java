package corallus.artConnect.artConnect.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import corallus.artConnect.artConnect.entity.TipoStatus;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;

@Component
public class TipoStatusSeeder implements CommandLineRunner{
    @Autowired
    private TipoStatusRepository tipoStatusRepository;


    @Override
    public void run(String... args) throws Exception {
        if(tipoStatusRepository.count() == 0) {
            TipoStatus ativo = new TipoStatus(null, "ATIVO");
            TipoStatus pendente = new TipoStatus(null, "PENDENTE");
            TipoStatus suspenso = new TipoStatus(null, "SUSPENSO");

            tipoStatusRepository.save(ativo);
            tipoStatusRepository.save(pendente);
            tipoStatusRepository.save(suspenso);
        }
        
    }
    
}
