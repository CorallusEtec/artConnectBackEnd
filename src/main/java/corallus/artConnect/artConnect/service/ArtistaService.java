package corallus.artConnect.artConnect.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> findAll() {
        try {
            return this.artistaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public String save(Artista artista) { 
        
        artista.setContatos(new ArrayList<Contato>());
        artista.setPublicacoes(new ArrayList<Publicacao>());
        artista.setReacoes(new HashSet<Reacao>());
        
        this.artistaRepository.save(artista);
        return "Artista cadastrado com sucesso!";
    }
}
