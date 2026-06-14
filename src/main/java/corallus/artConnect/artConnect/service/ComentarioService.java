package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.mapper.comentario.ComentarioMapper;
import corallus.artConnect.artConnect.queryFilter.ComentarioFindByPostQF;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.comentario.ComentarioRequest;
import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.entity.Publicacao;
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

    /** Busca os comentários a partir de um Id de publicação
     *
     * @param postId Id da publicação alvo.
     * @param pageable Configurações de busca da paginação.
     * @param queryFilter Configurações de filtros de busca.
     * @param usuario Referência do usuário autenticado.
     * @return Lista paginada com os detalhes do comentário.
     */
    public Page<ComentarioResponse> findByPost(Long postId, Pageable pageable, ComentarioFindByPostQF queryFilter, Usuario usuario) {
        System.out.println(usuario);
        Page<Comentario> comentarios = this.comentarioRepository.findAllByPublicacao_Id(postId, pageable, queryFilter.getSpecification());
        return comentarios.map(c->this.comentarioMapper.toDTO(c, Objects.isNull(usuario)?null:usuario.getId()));
    }

    /** Endpoint para comentar em uma publicação
     *
     * @param usuario Referência do usuário autenticado.
     * @param request Request com os dados do comentário.
     * @return Mensagem informando caso o comentario tenha sido publicado com sucesso.
     */
    public MessageResponse comentar(Usuario usuario, ComentarioRequest request) {

        Publicacao publicacao = this.publicacaoRepository.findById(request.idPublicacao())
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        Comentario comentario  = new Comentario();
        comentario.setMensagem(request.mensagem());
        comentario.setStatus(this.statusService.generateStatus());
        comentario.setDataComentario(LocalDateTime.now());
        comentario.setReacoes(new HashSet<>());
        comentario.setUsuario(this.usuarioRepository.findById(usuario.getId())
                .orElseThrow(NotAuthorizedException::new));
        comentario.setPublicacao(publicacao);

        this.comentarioRepository.save(comentario);
        return new MessageResponse("Comentario publicado com sucesso");
    }

}
