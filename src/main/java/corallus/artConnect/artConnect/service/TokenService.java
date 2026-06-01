package corallus.artConnect.artConnect.service;

import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import corallus.artConnect.artConnect.entity.atores.Usuario;


@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    // Gera token
    public String generateToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withIssuer("art-connect")
                .withClaim("roles", usuario.getTipoConta().name())
        .withSubject(usuario.getEmail())
        .withExpiresAt(Instant.now().plus(Duration.ofHours(3))) // Tokn expira em 3 horas
        .sign(algorithm);
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("art-connect")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

}
