package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.ContatoArtista;
import corallus.artConnect.artConnect.repository.ContatoArtistaRepository;

@Service
public class ContatoArtistaService {
    @Autowired
    private ContatoArtistaRepository contatoArtistaRepository;
    
    public List<ContatoArtista> findAll() {
        return contatoArtistaRepository.findAll();
    }

    public String cadastrarContato(ContatoArtista contato) {
        contatoArtistaRepository.save(contato);
        return "Contato cadastrado";
    }
}
