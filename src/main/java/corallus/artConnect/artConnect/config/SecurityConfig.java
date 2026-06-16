package corallus.artConnect.artConnect.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import corallus.artConnect.artConnect.security.SecurityFilter;


@Configuration
@EnableWebSecurity
@SecurityScheme(name = SecurityConfig.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public static final String SECURITY = "bearerAuth";

    // INJEÇÃO DE DEPENDÊNCIA
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            // ROTAS PÚBLICAS
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers("/doc.html/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()

                .requestMatchers("/usuario/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/artista/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/contratante/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/publicacao/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/comentario/findByPost/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/arte/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/generoArte/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/contato/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tipoContato/**").permitAll()

                .requestMatchers("/ws/**").permitAll()

                // ROTAS PROTEGIDAS POR ROLES
                /* ARTISTA */
                .requestMatchers(HttpMethod.PUT, "/artista/").hasAuthority("ARTISTA")
                /* CONTRATANTE */
                .requestMatchers(HttpMethod.PUT, "/contratante/").hasAuthority("CONTRATANTE")

                /* ADMIN */
                .requestMatchers(HttpMethod.POST, "/arte/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/arte/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/arte/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/generoArte/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/tipoContato/**").hasAuthority("ADMIN")
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/arte/save").hasAuthority("ADMIN")
                // WEB SOCKET


                // OUTRAS (AUTENTICAÇÃO)
                .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
