package corallus.artConnect.artConnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import corallus.artConnect.artConnect.security.SecurityFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;


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
                .requestMatchers(HttpMethod.GET, "/generoArte/**").permitAll()
            
                .requestMatchers("/usuario/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/artista/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/contratante/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/publicacao/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/comentario/findByPost/{id}").permitAll()
            

                .requestMatchers(HttpMethod.GET, "/arte/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/contato/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tipoContato/**").permitAll()
                // ROTAS PROTEGIDAS POR ROLES
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/arte/save").hasAuthority("ADMIN")

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
