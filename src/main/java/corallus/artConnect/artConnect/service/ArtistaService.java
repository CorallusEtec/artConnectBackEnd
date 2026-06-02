package corallus.artConnect.artConnect.service;

import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.mapper.artista.ArtistaMapper;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final ArtistaMapper artistaMapper;

    public ArtistaService(
            ArtistaRepository artistaRepository,
            ArtistaMapper artistaMapper
    ) {
        this.artistaRepository = artistaRepository;
        this.artistaMapper = artistaMapper;
    }

    public List<ArtistaResponse> findAll(ArtistaFindAllQF filter) {
        List<Artista> lista = this.artistaRepository.findAll(filter.toSpecification());
        return this.artistaMapper.toDTOList(lista);
    }

    public ArtistaResponse findById(Long id) {
        Artista artista = this.artistaRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

        return this.artistaMapper.toDTO(artista);
    }

    public MessageResponse edit(Long id, ArtistaEditRequest editRequest) {
        // VERIFICA SE O ARTISTA EXSITE NO BANCO
        Artista artista = this.artistaRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        artista.setNome(editRequest.nome());
        artista.setNomeArtistico(editRequest.nomeArtistico());
        artista.setDataNasc(editRequest.dataNasc());
        artista.setArte(editRequest.arte());

        // Logradouro
        UsuarioService.fillCommonEdits(artista, editRequest);

        this.artistaRepository.save(artista);
        return new MessageResponse("Artista atualizado com sucesso!");
    }
}