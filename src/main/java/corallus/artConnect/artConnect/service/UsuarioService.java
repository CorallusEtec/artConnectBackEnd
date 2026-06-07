package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.mapper.usuario.UsuarioMapper;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class UsuarioService {
    private final S3Client s3Client;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    // INJEÇÃO DE DEPENDÊNCIA
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, S3Client s3Client) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.s3Client = s3Client;
    }

    public Page<UsuarioResponse> findAll(UsuarioFindAllQF filter, Pageable pageable) {
        Page<Usuario> lista = this.usuarioRepository.findAll(filter.toSpecifications(), pageable);
        return lista.map(usuarioMapper::toDTO);
    }

    public UsuarioResponse findById(Long id) {
        Usuario entity = this.usuarioRepository.findById(id)
                .filter(u->u.getTipoConta() != ETipoConta.ADMIN)
            .orElseThrow(UserNotFoundException::new);
        return this.usuarioMapper.toDTO(entity);
    }

    public static void fillCommonEdits(Usuario u, CommonEdit dto) {
        u.setNomeLog(dto.nomeLog());
        u.setNumLog(dto.numLog());
        u.setCep(dto.cep());
        u.setBairro(dto.bairro());
        u.setComplemento(dto.complemento());
        u.setCidade(dto.cidade());
        u.setUf(dto.uf());
    }

    public MessageResponse updateFotoPerfil(MultipartFile file, Usuario usuario) throws IOException {
        if(file==null || file.isEmpty()) {
            throw new IllegalArgumentException("Campo de foto não pode ser vazio");
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Apenas imagens são permitidas");
        }

        String fileName = "perfil/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build(),
            RequestBody.fromBytes(file.getBytes())
        );
        String url = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;
        usuario.setFotoPerfilUrl(url);

        this.usuarioRepository.save(usuario);
         return new MessageResponse("Foto de perfil atualizada com sucesso!");
    }
}
