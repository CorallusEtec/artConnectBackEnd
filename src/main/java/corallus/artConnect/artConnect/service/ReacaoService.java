package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.factory.reacao.ReacaoFactory;
import corallus.artConnect.artConnect.mapper.reacao.ReacaoMapper;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

@Service
public class ReacaoService {
    private final ReacaoMapper reacaoMapper;
    private final UsuarioRepository usuarioRepository;
    private final ReacaoFactory reacaoFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoService(
            ReacaoMapper reacaoMapper,
            UsuarioRepository usuarioRepository,
            ReacaoFactory reacaoFactory
    ){
        this.reacaoMapper = reacaoMapper;
        this.usuarioRepository = usuarioRepository;
        this.reacaoFactory = reacaoFactory;
    }

    public ReacaoResponse reagir(ReacaoRequest reacaoRequest) {
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(reacaoRequest.idAutor())) {
            throw new UserNotFoundException("Usuario não encontrado");
        }
        Reacao reacaoEntity = this.reacaoFactory.createReacao(reacaoRequest);
        return this.reacaoMapper.toDTO(reacaoEntity);
    }
}
