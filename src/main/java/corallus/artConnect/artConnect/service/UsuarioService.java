package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.mapper.usuario.UsuarioMapper;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;


@Service
public class UsuarioService {
    private final S3Service s3Service;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public UsuarioService(
            UsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper,
            S3Service s3Service) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.s3Service = s3Service;
    }

    public Page<UsuarioResponse> findAll(UsuarioFindAllQF filter, Pageable pageable) {
        Page<Usuario> lista = this.usuarioRepository.findAll(filter.toSpecifications(), pageable);
        
        return lista.map(usuario -> {
            UsuarioResponse base = usuarioMapper.toDTO(usuario);
            
            if (usuario instanceof Artista artista) {
                return new UsuarioResponse(
                    base.id(), base.nome(), base.email(), 
                    base.tipoConta(), base.status(), base.dataCriacao(), 
                    base.fotoPerfilUrl(), base.nomeLog(), base.numLog(), 
                    base.cep(), base.bairro(), base.complemento(), 
                    base.cidade(), base.uf(), base.textoBio(), 
                    base.contatos(), artista.getArte(), artista.getGenerosArte(),
                    base.totalSeguidores(), base.totalSeguindo()
                );
            }
            
            return base;
        });
    }

    public UsuarioResponse findById(Long id) {
        Usuario entity = this.usuarioRepository.findById(id)
                .filter(u -> u.getTipoConta() != ETipoConta.ADMIN)
                .orElseThrow(UserNotFoundException::new);

        UsuarioResponse base = this.usuarioMapper.toDTO(entity);

        if (entity instanceof Artista artista) {
            return new UsuarioResponse(
                base.id(), base.nome(), base.email(), base.tipoConta(),
                base.status(), base.dataCriacao(), base.fotoPerfilUrl(),
                base.nomeLog(), base.numLog(), base.cep(), base.bairro(),
                base.complemento(), base.cidade(), base.uf(),
                base.textoBio(), base.contatos(),
                artista.getArte(), artista.getGenerosArte(),
                base.totalSeguidores(), base.totalSeguindo()
            );
        }

        return base;
    }

    public static void fillCommonEdits(Usuario u, CommonEdit dto) {
        u.setNomeLog(dto.nomeLog());
        u.setNumLog(dto.numLog());
        u.setTextoBio(dto.textoBio());
        u.setCep(dto.cep());
        u.setBairro(dto.bairro());
        u.setComplemento(dto.complemento());
        u.setCidade(dto.cidade());
        u.setUf(dto.uf());
    }

    /**
     * Atualiza a foto de perfil do usuário.
     *
     * @param file Referência do arquivo da foto.
     * @param usuario Referência do usuário autenticado.
     * @return Mensagem caso a foto tenha sido atualizada
     * @throws Exception Erro de I/O ao carregar o arquivo.
     */
    public MessageApiResponse updateFotoPerfil(MultipartFile file, Usuario usuario) throws Exception {
        if(file==null || file.isEmpty()) {
            throw new IllegalArgumentException("Campo de foto não pode ser vazio");
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Apenas imagens são permitidas");
        }

        String url = this.s3Service.uploadFotoPerfil(file);
        usuario.setFotoPerfilUrl(url);

        this.usuarioRepository.save(usuario);
         return new MessageApiResponse("Foto de perfil atualizada com sucesso!");
    }
}
