package corallus.artConnect.artConnect.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import corallus.artConnect.artConnect.entity.status.TipoStatus;
import corallus.artConnect.artConnect.enums.ListaTipoStatus;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Component
public class TipoStatusSeeder implements CommandLineRunner{
    @Autowired
    private TipoStatusRepository tipoStatusRepository;


    @Override
    public void run(String... args) throws Exception {
        if(tipoStatusRepository.count() == 0) {
            TipoStatus ativo = new TipoStatus(null, ListaTipoStatus.ATIVO.name());
            TipoStatus pendente = new TipoStatus(null, ListaTipoStatus.PENDENTE.name());
            TipoStatus suspenso = new TipoStatus(null, ListaTipoStatus.SUSPENSO.name());
            TipoStatus excluido = new TipoStatus(null, ListaTipoStatus.EXCLUIDO.name());

            tipoStatusRepository.save(ativo);
            tipoStatusRepository.save(pendente);
            tipoStatusRepository.save(suspenso);
            tipoStatusRepository.save(excluido);
        }
        
    }
    
}
