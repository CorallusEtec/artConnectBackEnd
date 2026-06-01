package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.mapper.artista.ArtistaCadastroMapper;
import corallus.artConnect.artConnect.mapper.artista.ArtistaMapper;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaCadastroRequest;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;

@Service
public class ArtistaService {

    private final UsuarioRepository usuarioRepository;

    private final ArtistaRepository artistaRepository;

    private final StatusService statusService;

    private final ArtistaMapper artistaMapper;

    private final ArtistaCadastroMapper artistaCadastroMapper;

    public ArtistaService(UsuarioRepository usuarioRepository,
                          ArtistaRepository artistaRepository,
                          StatusService statusService,
                          ArtistaMapper artistaMapper,
                          ArtistaCadastroMapper artistaCadastroMapper) {
        this.usuarioRepository = usuarioRepository;
        this.artistaRepository = artistaRepository;
        this.statusService = statusService;
        this.artistaMapper = artistaMapper;
        this.artistaCadastroMapper = artistaCadastroMapper;
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

    public MessageResponse save(ArtistaCadastroRequest cadastroRequest) {
        if(this.usuarioRepository.existsByEmail(cadastroRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        Artista artista = this.artistaCadastroMapper.toEntity(cadastroRequest);
        artista.setId(null); // Garantir que não sobrescreva no banco

        // STATUS PADRÃO DE CRIAÇÂO: ATIVO
        artista.setStatus(this.statusService.generateStatus());

        // DATA E TIPO DE CONTA
        artista.setDataCriacao(LocalDateTime.now());
        artista.setTipoConta(ETipoConta.ARTISTA);
        
        this.artistaRepository.save(artista);
        return new MessageResponse("Artista cadastrado com sucesso!");
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