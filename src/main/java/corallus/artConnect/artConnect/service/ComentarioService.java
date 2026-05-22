package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.request.comentario.ComentarioRequest;
import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.status.Status;
import corallus.artConnect.artConnect.enums.ListaTipoStatus;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.status.StatusRepository;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Service
public class ComentarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TipoStatusRepository tipoStatusRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private StatusRepository statusRepository;
    
    // Buscar Comentários de uma postagem
    public List<ComentarioResponse> findByPost(Long postId) {
        Publicacao p = this.publicacaoRepository.findById(postId)
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        /**
         * SE o Status da publicação não for ATIVO, gera uma 
         * exceção (Tentando acessar publicação bloqueada ou )
         */  
        
        if(!p.getStatusPublicacao()
            .getTipoStatus()
            .getNomeTipoStatus()
            .equals(ListaTipoStatus.ATIVO.name())
        ) {
            throw new IllegalArgumentException("Publicação Indisponível");
        }


        // Conversão para DTO
        List<ComentarioResponse> comentarios = p.getComentarios()
        .stream()
        .filter(
            c->c.getStatusComentario()
            .getTipoStatus()
            .getNomeTipoStatus()
            .equals(ListaTipoStatus.ATIVO.name()))
        .map(ComentarioResponse::toDTO)
        .collect(Collectors.toList());

        return comentarios;
    }

    // Comentar em uma publicação
    public String comentar(ComentarioRequest dto) {

        Publicacao publicacao = this.publicacaoRepository.findById(dto.idPublicacao())
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        Comentario comentario  = requestToEntity(dto);
        comentario.setPublicacao(publicacao);
        
        comentario.setDataComentario(LocalDateTime.now());
        


        
        this.comentarioRepository.save(comentario);
        return "Comentario publicado com sucesso";
    }

    // Encapsular Conversão da Request para Entity
    private Comentario requestToEntity(ComentarioRequest dto) {
        Comentario comentario = new Comentario();
        comentario.setMensagem(dto.mensagem());

        // Usuario Autor do comentário. Caso não exista, já lança uma exceção
        comentario.setUsuario(this.usuarioRepository
            .findById(dto.idAutor())
            .orElseThrow(()-> new UserNotFoundException()));
        
        // Reações Começa zerado e status é o padrão.
        comentario.setReacoes(new HashSet<>());
        
        // criação dp status padrão.
        Status stComentario = new Status();
        stComentario.setDataModificacao(LocalDateTime.now());
        stComentario.setTipoStatus(this.tipoStatusRepository
            .findByNomeTipoStatus(ListaTipoStatus.ATIVO.name())
            .get());
        stComentario = this.statusRepository.save(stComentario);

        // status adicionado
        comentario.setStatusComentario(stComentario);
        return comentario;
    }

}
