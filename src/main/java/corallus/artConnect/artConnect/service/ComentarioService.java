package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.mapper.comentario.ComentarioMapper;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.comentario.ComentarioRequest;
import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;

@Service
public class ComentarioService {

    private final PublicacaoRepository publicacaoRepository;

    private final ComentarioRepository comentarioRepository;

    private final StatusService statusService;

    private final UsuarioRepository usuarioRepository;

    private final ComentarioMapper comentarioMapper;

    // INJEÇÃO DE DEPENDÊNCIA


    public ComentarioService(PublicacaoRepository publicacaoRepository,
                             ComentarioRepository comentarioRepository,
                             StatusService statusService,
                             UsuarioRepository usuarioRepository,
                             ComentarioMapper comentarioMapper) {
        this.publicacaoRepository = publicacaoRepository;
        this.comentarioRepository = comentarioRepository;
        this.statusService = statusService;
        this.usuarioRepository = usuarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    // Buscar Comentários de uma postagem
    public List<ComentarioResponse> findByPost(Long postId) {
        Publicacao p = this.publicacaoRepository.findById(postId)
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        /*
         * SE o Status da publicação não for ATIVO, gera uma 
         * exceção (Tentando acessar publicação bloqueada ou )
         */
        if(p.getStatusPublicacao()
            .getTipoStatus()
            .getNomeTipoStatus() != ETipoStatus.ATIVO
        ) { throw new IllegalArgumentException("Publicação Indisponível"); }


        // Conversão para DTO
        List<Comentario> comentarios = p.getComentarios()
        .stream().filter(
            c->c.getStatusComentario()
            .getTipoStatus().getNomeTipoStatus()
            .equals(ETipoStatus.ATIVO))
        .collect(Collectors.toList());

        return this.comentarioMapper.toDTOList(comentarios);
    }

    // Comentar em uma publicação
    public MessageResponse comentar(Long idUsuario, ComentarioRequest request) {

        Publicacao publicacao = this.publicacaoRepository.findById(request.idPublicacao())
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        Comentario comentario  = new Comentario();
        comentario.setMensagem(request.mensagem());
        comentario.setStatusComentario(this.statusService.generateStatus());
        comentario.setDataComentario(LocalDateTime.now());
        comentario.setReacoes(new HashSet<>());
        comentario.setUsuario(this.usuarioRepository.findById(idUsuario)
                .orElseThrow(NotAuthorizedException::new));
        comentario.setPublicacao(publicacao);

        this.comentarioRepository.save(comentario);
        return new MessageResponse("Comentario publicado com sucesso");
    }

}
