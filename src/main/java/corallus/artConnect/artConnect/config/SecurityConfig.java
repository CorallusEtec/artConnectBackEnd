package corallus.artConnect.artConnect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import corallus.artConnect.artConnect.security.SecurityFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
            
            .requestMatchers(HttpMethod.GET, "/usuario/findAll").permitAll()
            .requestMatchers(HttpMethod.GET, "/usuario/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/artista/findAll").permitAll()
            .requestMatchers(HttpMethod.GET, "/artista/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/contratante/findAll").permitAll()
            .requestMatchers(HttpMethod.GET, "/publicacao/findAll").permitAll()
            .requestMatchers(HttpMethod.GET, "/comentario/findByPost/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/tag/findAll").permitAll()
            
            .requestMatchers(HttpMethod.GET, "/arte/findAll").permitAll()
            .requestMatchers(HttpMethod.GET, "/arte/**").permitAll()
            
            .requestMatchers(HttpMethod.GET, "/contato/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/tipoContato/findAll").permitAll()
                .requestMatchers(HttpMethod.GET, "/tipoContato/**").permitAll()
            .anyRequest().authenticated()
        )

        .exceptionHandling(handling -> handling
                .authenticationEntryPoint((request, response, authException) -> {
                    resolver.resolveException(request, response, http, authException);
                })
            )


        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
