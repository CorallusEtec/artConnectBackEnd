package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.enums.ListaTipoReacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.ComentarioRepository;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.reacao.ReacaoRepository;
import corallus.artConnect.artConnect.repository.reacao.TipoReacaoRepository;

@Service
public class ReacaoService {
    @Autowired
    private ReacaoRepository reacaoRepository;

    @Autowired
    private TipoReacaoRepository tipoReacaoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public ReacaoResponse reagirPublicacao(Long postId, ReacaoRequest reacaoPostDTO) {
        
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(reacaoPostDTO.idAutor())) {
            throw new UserNotFoundException("Usuario não encontrado");
        }

        // Se a publicação não existir
        if(!this.publicacaoRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Publicação não encontrado");
        }

        // Validação do tipo de reação
        if(!reacaoValida(reacaoPostDTO.nomeTipoReacao())) {
            throw new IllegalArgumentException("Tipo de Reação inválida");
        }

        Optional<Reacao> reacaoAtual =  reacaoRepository.findByUsuario_IdAndPublicacao_Id(reacaoPostDTO.idAutor(), postId);

        Reacao reacaoEntity;


        // Se já tem uma reação nessa postagem
        if(reacaoAtual.isPresent()) {
            reacaoEntity =  reacaoAtual.get();


            // Se for o mesmo tipo de reação
            if(reacaoEntity.getTipoReacao().getNomeTipo().equalsIgnoreCase(reacaoPostDTO.nomeTipoReacao())) {
                // Exclui a reação
                this.reacaoRepository.deleteById(reacaoEntity.getId());
                return ReacaoResponse.emptyDto();
            } else {
                // Senão, apenas alternar o tipo


                reacaoEntity.setDataReacao(LocalDateTime.now());
                reacaoEntity.setTipoReacao(this.tipoReacaoRepository.findByNomeTipo(reacaoPostDTO.nomeTipoReacao().toUpperCase()).get());

                // Salvando reação alterada
                this.reacaoRepository.save(reacaoEntity);
            }

            return ReacaoResponse.toDTO(reacaoEntity);
        
            // Se não tem reação nessa postagem
        } else {
            // Criar a reação e persisti-la
            reacaoEntity = new Reacao();

            reacaoEntity.setDataReacao(LocalDateTime.now());

            reacaoEntity.setTipoReacao(this.tipoReacaoRepository.findByNomeTipo(reacaoPostDTO.nomeTipoReacao().toUpperCase()).get());

            
            reacaoEntity.setPublicacao(
                this.publicacaoRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Publicação não encontrada"))
            );
            
            
            reacaoEntity.setUsuario(
                this.usuarioRepository.findById(reacaoPostDTO.idAutor()).orElseThrow(()->new UserNotFoundException("Usuário não encontrado"))
            );

            // Salvar nova reação
            this.reacaoRepository.save(reacaoEntity);
        }

        return ReacaoResponse.toDTO(reacaoEntity);
    }

    public ReacaoResponse getReacaoPublicacao(Long postId, Long usuarioId) {
      
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(usuarioId)) {
            throw new UserNotFoundException("Usuario não encontrado");
        }

        // Se a publicação não existir
        if(!this.publicacaoRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Publicação não encontrado");
        }


        Optional<Reacao> reacaoAtual =  reacaoRepository.findByUsuario_IdAndPublicacao_Id(usuarioId, postId);

        // Se existe uma reação
        if(reacaoAtual.isPresent()) {
            return ReacaoResponse.toDTO(reacaoAtual.get());
        } else {
        // Se não
            return ReacaoResponse.emptyDto();
        }
    }

    public ReacaoResponse reagirComentario(Long commentId, ReacaoRequest reacaoPostDTO) {
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(reacaoPostDTO.idAutor())) {
            throw new UserNotFoundException("Usuario não encontrado");
        }

        // Se o comentário não existir
        if(!this.comentarioRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comentário não encontrado");
        }

        // Validação do tipo de reação
        if(!reacaoValida(reacaoPostDTO.nomeTipoReacao())) {
            throw new IllegalArgumentException("Tipo de Reação inválida");
        }

        Optional<Reacao> reacaoAtual =  reacaoRepository.findByUsuario_IdAndComentario_Id(reacaoPostDTO.idAutor(), commentId);

        Reacao reacaoEntity;


        // Se já tem uma reação nesse comentário
        if(reacaoAtual.isPresent()) {
            reacaoEntity =  reacaoAtual.get();


            // Se for o mesmo tipo de reação
            if(reacaoEntity.getTipoReacao().getNomeTipo().equalsIgnoreCase(reacaoPostDTO.nomeTipoReacao())) {
                // Exclui a reação
                this.reacaoRepository.deleteById(reacaoEntity.getId());
                return ReacaoResponse.emptyDto();
            } else {
                // Senão, apenas alternar o tipo


                reacaoEntity.setDataReacao(LocalDateTime.now());
                reacaoEntity.setTipoReacao(this.tipoReacaoRepository.findByNomeTipo(reacaoPostDTO.nomeTipoReacao().toUpperCase()).get());

                // Salvando reação alterada
                this.reacaoRepository.save(reacaoEntity);
            }

            return ReacaoResponse.toDTO(reacaoEntity);
        
            // Se não tem reação nesse comentário
        } else {
            // Criar a reação e persisti-la
            reacaoEntity = new Reacao();

            reacaoEntity.setDataReacao(LocalDateTime.now());

            reacaoEntity.setTipoReacao(this.tipoReacaoRepository.findByNomeTipo(reacaoPostDTO.nomeTipoReacao().toUpperCase()).get());

            
            reacaoEntity.setComentario(
                this.comentarioRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comentário não encontrado"))
            );
            
            
            reacaoEntity.setUsuario(
                this.usuarioRepository.findById(reacaoPostDTO.idAutor()).orElseThrow(()->new UserNotFoundException("Usuário não encontrado"))
            );

            // Salvar nova reação
            this.reacaoRepository.save(reacaoEntity);
        }

        return ReacaoResponse.toDTO(reacaoEntity);
    }

    public ReacaoResponse getReacaoComentario(Long commentId, Long usuarioId) {
      
        // Se o usuario não exisitir
        if(!this.usuarioRepository.existsById(usuarioId)) {
            throw new UserNotFoundException("Usuario não encontrado");
        }

        // Se o comentário não existir
        if(!this.comentarioRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comentário não encontrado");
        }


        Optional<Reacao> reacaoAtual =  reacaoRepository.findByUsuario_IdAndComentario_Id(usuarioId, commentId);

        // Se existe uma reação
        if(reacaoAtual.isPresent()) {
            return ReacaoResponse.toDTO(reacaoAtual.get());
        } else {
        // Se não
            return ReacaoResponse.emptyDto();
        }
    }



    private boolean reacaoValida(String reacao) {
        for(ListaTipoReacao s : ListaTipoReacao.values()) {
            if(s.name().equalsIgnoreCase(reacao.trim())) {
                return true;
            }
        }
        return false;
    }
}
