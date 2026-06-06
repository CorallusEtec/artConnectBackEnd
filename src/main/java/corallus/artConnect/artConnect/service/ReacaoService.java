package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.factory.reacao.ReacaoFactory;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import java.util.Objects;


@Service
public class ReacaoService {
    private final UsuarioRepository usuarioRepository;
    private final ReacaoFactory reacaoFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoService(
            UsuarioRepository usuarioRepository,
            ReacaoFactory reacaoFactory
    ){
        this.usuarioRepository = usuarioRepository;
        this.reacaoFactory = reacaoFactory;
    }

    public MessageResponse reagir(Long idAutor, ReacaoRequest reacaoRequest) {
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(idAutor)) {
            throw new NotAuthorizedException();
        }
        Reacao reacaoSaved = this.reacaoFactory.createReacao(idAutor, reacaoRequest);
        // SE FOR A MESMA REAÇÃO (O Retorno do Factory for null) ADAPTA A MENSAGEM
        return new MessageResponse(Objects.isNull(reacaoSaved)?"Desreagido":"Reagido");
    }
}
