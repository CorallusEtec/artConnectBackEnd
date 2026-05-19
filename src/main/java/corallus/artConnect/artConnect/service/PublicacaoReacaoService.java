package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.publicacao.ReacaoDTO;
import corallus.artConnect.artConnect.dto.publicacao.ReacaoPostDTO;
import corallus.artConnect.artConnect.entity.ListaTipoReacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.TipoReacaoRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.publicacao.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.publicacao.ReacaoRepository;

@Service
public class PublicacaoReacaoService {
    @Autowired
    private ReacaoRepository reacaoRepository;

    @Autowired
    private TipoReacaoRepository tipoReacaoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ReacaoDTO reagir(Long postId, ReacaoPostDTO reacaoPostDTO) {
        
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

                this.reacaoRepository.deleteById(reacaoEntity.getId());
                return ReacaoDTO.emptyDto();
            } else {
                // Senão, apenas alternar o tipo


                reacaoEntity.setDataReacao(LocalDateTime.now());
                reacaoEntity.setTipoReacao(this.tipoReacaoRepository.findByNomeTipo(reacaoPostDTO.nomeTipoReacao().toUpperCase()).get());

                // Salvando reação alterada
                this.reacaoRepository.save(reacaoEntity);
            }

            return ReacaoDTO.toDTO(reacaoEntity);
        
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

        return ReacaoDTO.toDTO(reacaoEntity);
    }

    public ReacaoDTO getReacao(Long postId, Long usuarioId) {
      
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
            return ReacaoDTO.toDTO(reacaoAtual.get());
        } else {
        // Se não
            return ReacaoDTO.emptyDto();
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
