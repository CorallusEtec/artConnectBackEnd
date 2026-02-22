package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.ArtistaDTO;
import corallus.artConnect.artConnect.repository.ArtistaRepository;

@Service
public class ArtistaService {
    
    @Autowired
    private ArtistaRepository artistaRepository;

    public String cadastro(ArtistaDTO artista) {
        try {
            artistaRepository.save(artista);
            return "Cadastro realizado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar artista: " + e.getMessage();
        }
    }

    public List<ArtistaDTO> findAll() {
        try {
            List<ArtistaDTO> artistas = artistaRepository.findAll();
            return artistas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar artistas: " + e.getMessage());
        }
    }
}
