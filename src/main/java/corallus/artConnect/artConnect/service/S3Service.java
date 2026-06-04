package corallus.artConnect.artConnect.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .contentType(file.getContentType())
            .build();

            s3client.putObject(
                request,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
            );
        return "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;

    }
}
