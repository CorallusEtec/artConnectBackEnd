package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.comentario.ComentarioGetDTO;
import corallus.artConnect.artConnect.dto.comentario.ComentarioPostDTO;
import corallus.artConnect.artConnect.entity.ListaTipoStatus;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.publicacao.Comentario;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.StatusRepository;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.publicacao.ComentarioRepository;
import corallus.artConnect.artConnect.repository.publicacao.PublicacaoRepository;

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
    public List<ComentarioGetDTO> findByPost(Long postId) {
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
        List<ComentarioGetDTO> comentarios = p.getComentarios()
        .stream()
        .filter(
            c->c.getStatusComentario()
            .getTipoStatus()
            .getNomeTipoStatus()
            .equals(ListaTipoStatus.ATIVO.name()))
        .map(ComentarioGetDTO::toDTO)
        .collect(Collectors.toList());

        return comentarios;
    }

    // Comentar em uma publicação
    public String comentar(ComentarioPostDTO dto) {

        Publicacao publicacao = this.publicacaoRepository.findById(dto.idPublicacao())
        .orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"));

        Comentario comentario  = postToEntity(dto);
        comentario.setPublicacao(publicacao);
        


        
        this.comentarioRepository.save(comentario);
        return "Comentario publicado com sucesso";
    }

    // Encapsular Conversão do DTO de POST para Entity
    private Comentario postToEntity(ComentarioPostDTO dto) {
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
