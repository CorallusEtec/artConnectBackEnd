package corallus.artConnect.artConnect.service;

import java.util.*;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.mapper.publicacao.PublicacaoMapper;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final StatusService statusService;
    private final PublicacaoMapper publicacaoMapper;
    private final S3Client s3Client;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoService(PublicacaoRepository publicacaoRepository,
                             UsuarioRepository usuarioRepository,
                             StatusService statusService,
                             PublicacaoMapper publicacaoMapper,
                             S3Client s3Client) {
        this.publicacaoRepository = publicacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.statusService = statusService;
        this.publicacaoMapper = publicacaoMapper;
        this.s3Client = s3Client;
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public MessageResponse save(String legenda, MultipartFile file, Long autorId) {
        try {

            boolean temLegenda = legenda != null && !legenda.isBlank();
            boolean temImagem = file != null && !file.isEmpty();

            if (!temLegenda && !temImagem) {
                throw new IllegalArgumentException("O Post deve ter ao menos imagem ou legenda");
            }

            String url = null;

            if (temImagem) {

                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(fileName)
                                .contentType(file.getContentType())
                                .build(),
                        RequestBody.fromBytes(file.getBytes())
                );
                url = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;
            }

            Usuario autor = usuarioRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Publicacao pub = new Publicacao();
            pub.setLegenda(temLegenda ? legenda : null);
            pub.setUrlMidia(url);
            pub.setAutor(autor);

            // STATUS PADRÃO DE CRIAÇÂO: ATIVO
            pub.setStatusPublicacao(this.statusService.generateStatus());

            publicacaoRepository.save(pub);
            return new MessageResponse("Postagem criada com sucesso!");

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar publicação", e);
        }
    }

    public List<PublicacaoResponse> findAll(PublicacaoFindAllQF find) {
        var entityList = this.publicacaoRepository.findAll(find.toSpecifications());
        return this.publicacaoMapper.toDTOList(entityList);
    }
}