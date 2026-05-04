package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.dto.publicacao.PublicacaoDTO;
import corallus.artConnect.artConnect.entity.ListaTipoStatus;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.repository.StatusRepository;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.publicacao.PublicacaoRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PublicacaoService {

	@Autowired
    private PublicacaoRepository publicacaoRepository;
	@Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoStatusRepository tipoStatusRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
	private S3Client s3Client;
    
    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String criarPublicacao(String legenda, MultipartFile file, Long autorId) {
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

            // STATUS PADRÃO DE CRIAÇÂO: ATIVO
            Status statusInicial = new Status();
            statusInicial.setTipoStatus(
                this.tipoStatusRepository
                    .findByNomeTipoStatus(ListaTipoStatus.ATIVO.name())
                    .get()
            );
            statusInicial.setDataModificacao(LocalDateTime.now());

            this.statusRepository.save(statusInicial);

            Usuario autor = usuarioRepository.findById(autorId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Publicacao pub = new Publicacao();
            pub.setLegenda(temLegenda ? legenda : null);
            pub.setUrlMidia(url);
            pub.setAutor(autor);
            pub.setStatusPublicacao(statusInicial);
            


            publicacaoRepository.save(pub);

            return "Postagem criada com sucesso!";

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar publicação", e);
        }
    }

    public List<PublicacaoDTO> listarPublicacoes() {
        return publicacaoRepository
        		.findByStatusPublicacao_TipoStatus_IdOrderByDataPublicacaoDesc(1L)
                .stream()
                .map(PublicacaoDTO::toDTO)
                .toList();
    }
}