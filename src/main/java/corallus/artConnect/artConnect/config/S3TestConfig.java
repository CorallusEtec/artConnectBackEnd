package corallus.artConnect.artConnect.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3TestConfig {

    @Bean
    CommandLineRunner testS3(S3Client s3Client) {
        return args -> {
            System.out.println("Testando conexão com S3");

            s3Client.listBuckets().buckets().forEach(bucket -> {
                System.out.println("Bucket: " + bucket.name());
            });

            System.out.println("Conexão OK!");
        };
    }
}
