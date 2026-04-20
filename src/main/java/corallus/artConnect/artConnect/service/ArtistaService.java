package corallus.artConnect.artConnect.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.ArtistaCadastroDTO;
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
    
    public String save(ArtistaCadastroDTO artistaDTO) { 
        
        Artista artista = new Artista();
        artista.setNome(artistaDTO.nome());
        artista.setEmail(artistaDTO.email());
        artista.setSenha(artistaDTO.senha());

        // Listas vazias por padrão no cadastro
        artista.setContatos(new ArrayList<Contato>());
        artista.setPublicacoes(new ArrayList<Publicacao>());
        artista.setReacoes(new HashSet<Reacao>());


        // STATUS PADRÃO DE CRIAÇÂO: ATIVO
        Status statusInicial = new Status(null, 
            this.tipoStatusRepository.findByNomeTipoStatus("ATIVO").get(),
            "",
            LocalDateTime.now());

        artista.setStatus(statusInicial);
        
        this.artistaRepository.save(artista);
        return "Artista cadastrado com sucesso!";
    }
}
