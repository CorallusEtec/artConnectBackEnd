package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.repository.ArtistaRepository;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> findAll() {
        return this.artistaRepository.findAll();
    }
    public String save(Artista artista) {
        this.artistaRepository.save(artista);
        return "Artista cadastrado com sucesso!";
    }
}
