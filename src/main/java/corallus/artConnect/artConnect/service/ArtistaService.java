package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.mapper.artista.ArtistaMapper;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ArtistaResponse> findAll(ArtistaFindAllQF filter, Pageable pageable) {
        Page<Artista> lista = this.artistaRepository.findAll(filter.toSpecification(), pageable);
        return lista.map(artistaMapper::toDTO);
    }

    public ArtistaResponse findById(Long id) {
        Artista artista = this.artistaRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

        return this.artistaMapper.toDTO(artista);
    }

    public MessageResponse edit(Long id, ArtistaEditRequest editRequest) {
        // VERIFICA SE O ARTISTA EXSITE NO BANCO
        Artista artista = this.artistaRepository.findById(id)
                .orElseThrow(NotAuthorizedException::new);

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