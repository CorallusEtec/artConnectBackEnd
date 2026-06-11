package corallus.artConnect.artConnect.service;

import java.util.Objects;
import java.util.UUID;
import corallus.artConnect.artConnect.enumeration.ETipoMidia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
    private final S3Client s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3client) {
        this.s3client = s3client;
    }

    /**
     * Faz o upload do arquivo à sua respectiva pasta no S3 e retorna a url.
     *
     * @param file Referência do arquivo a ser salvo.
     * @param fileName Nome do arquivo com pasta configurada para salvamento.
     * @return A url do arquivo.
     * @throws Exception Erro de I/O ao carregar o arquivo.
     */
    public String uploadFile(String fileName, MultipartFile file) throws Exception {
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .contentType(file.getContentType())
            .build();

            s3client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
            );
        return "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;
    }

    /**
     * Realiza upload da foto de perfil do usuário.
     *
     * @param file Referência do arquivo a ser salvo.
     * @return A url do arquivo.
     * @throws Exception Erro de I/O ao carregar o arquivo.
     */
    public String uploadFotoPerfil(MultipartFile file) throws Exception {
        String fileName = "perfil/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        return this.uploadFile(fileName, file);
    }

    /**
     * Realiza upload de arquivo da publicação.
     *
     * @param file Referência do arquivo a ser salvo.
     * @param tipoMidia Tipo de arquivo que será salvo.
     * @return A url do arquivo.
     * @throws Exception Erro de I/O ao carregar o arquivo.
     */
    public String uploadArquivoPublicacao(MultipartFile file, ETipoMidia tipoMidia) throws Exception {
        // SE NÃO TIVER ARQUIVO OU TIPO MIDIA FOR NULL
        if((Objects.isNull(file) || file.isEmpty()) || Objects.isNull(tipoMidia)) {
            return null;
        }
        String pasta = tipoMidia.name().toLowerCase() + "/";
        String fileName = pasta + UUID.randomUUID() + "-" + file.getOriginalFilename();
        return this.uploadFile(fileName, file);
    }
}
