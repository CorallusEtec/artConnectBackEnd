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

    // Salva um novo artista no banco de dados
    public String cadastro(Artista artista) {
        try {
            artistaRepository.save(artista);
            return "Cadastro realizado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar artista: " + e.getMessage();
        }
    }
    // Busca todos os artistas cadastrados no banco de dados
    public List<Artista> findAll() {
        try {
            List<Artista> artistas = artistaRepository.findAll();
            return artistas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar artistas: " + e.getMessage());
        }
    }
}
