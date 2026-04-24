package corallus.artConnect.artConnect.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.dto.publicacao.PublicacaoDTO;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.publicacao.PublicacaoRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final S3Client s3Client;

    private final String bucketName = "corallus-art-connect-bucket";

    public PublicacaoService(PublicacaoRepository publicacaoRepository,
                             UsuarioRepository usuarioRepository,
                             S3Client s3Client) {
        this.publicacaoRepository = publicacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.s3Client = s3Client;
    }

    public PublicacaoDTO criarPublicacao(String legenda, MultipartFile file, Long autorId) {
        try {

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );

            String url = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;

            Usuario autor = usuarioRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Publicacao pub = new Publicacao();
            pub.setLegenda(legenda);
            pub.setUrlMidia(url);
            pub.setAutor(autor);
            return new PublicacaoDTO(publicacaoRepository.save(pub));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar publicação", e);
        }
    }

    public List<PublicacaoDTO> listarPublicacoes() {
        return publicacaoRepository.findAll()
                .stream()
                .map(PublicacaoDTO::new)
                .toList();
    }
}