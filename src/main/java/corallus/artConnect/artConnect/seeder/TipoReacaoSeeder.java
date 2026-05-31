package corallus.artConnect.artConnect.seeder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import corallus.artConnect.artConnect.entity.reacao.TipoReacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.repository.reacao.TipoReacaoRepository;

@Component
public class TipoReacaoSeeder implements CommandLineRunner {
    @Autowired
    private TipoReacaoRepository tipoReacaoRepository;


    @Override
    public void run(String...args) throws Exception {
       if (tipoReacaoRepository.count() == 0) {
            tipoReacaoRepository.save(new TipoReacao(null, ETipoReacao.LIKE.name(), new ArrayList<>()));
            tipoReacaoRepository.save(new TipoReacao(null, ETipoReacao.DISLIKE.name(), new ArrayList<>()));
       }
    }
}
