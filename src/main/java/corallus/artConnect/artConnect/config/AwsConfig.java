package corallus.artConnect.artConnect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AwsConfig {

    @Value("${aws.region:sa-east-1}")
    private String region;

    @Bean
    public S3Client s3Client() {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        String accessKeyId = dotenv.get("AWS_ACCESS_KEY_ID");
        String secretAccessKey = dotenv.get("AWS_SECRET_ACCESS_KEY");
        
        if (accessKeyId == null || secretAccessKey == null) {
            throw new IllegalStateException(
                "variaveis de ambiente nao configuradas"
            );
        }
        
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        
        System.out.println("ACCESS KEY: " + accessKeyId);
        System.out.println("SECRET: " + secretAccessKey);
        
        return S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
            .build();
    }
}