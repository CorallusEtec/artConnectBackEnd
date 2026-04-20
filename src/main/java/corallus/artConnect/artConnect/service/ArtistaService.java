package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private TipoStatusRepository tipoStatusRepository;

    public List<Artista> findAll() {
        try {
            return this.artistaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public Artista findById(Long id) {
        return this.artistaRepository.findById(id).orElse(null);
    }

    public String save(Artista artista) { 
       
        artista.setContatos(new ArrayList<Contato>());
        artista.setPublicacoes(new ArrayList<Publicacao>());
        artista.setReacoes(new HashSet<Reacao>());



        Status statusInicial = new Status(null, 
            this.tipoStatusRepository.findByNomeTipoStatus("ATIVO").get(),
            "",
            LocalDateTime.now());

        artista.setStatus(statusInicial);
        
        this.artistaRepository.save(artista);
        return "Artista cadastrado com sucesso!";
    }

  
    public String edit(Artista artista) {
      
        if (artista.getId() == null || !this.artistaRepository.existsById(artista.getId())) {
            return "Erro: Artista não encontrado para edição.";
        }

        this.artistaRepository.save(artista);
        return "Artista atualizado com sucesso!";
    }
}