package corallus.artConnect.artConnect.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import corallus.artConnect.artConnect.entity.status.TipoStatus;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Component
public class TipoStatusSeeder implements CommandLineRunner{
    @Autowired
    private TipoStatusRepository tipoStatusRepository;


    @Override
    public void run(String... args) throws Exception {
        if(tipoStatusRepository.count() == 0) {
            TipoStatus ativo = new TipoStatus(null, ETipoStatus.ATIVO.name());
            TipoStatus pendente = new TipoStatus(null, ETipoStatus.PENDENTE.name());
            TipoStatus suspenso = new TipoStatus(null, ETipoStatus.SUSPENSO.name());
            TipoStatus excluido = new TipoStatus(null, ETipoStatus.EXCLUIDO.name());

            tipoStatusRepository.save(ativo);
            tipoStatusRepository.save(pendente);
            tipoStatusRepository.save(suspenso);
            tipoStatusRepository.save(excluido);
        }
        
    }
    
}
