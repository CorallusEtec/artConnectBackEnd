package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.factory.usuario.UsuarioFactory;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.usuario.UserLoginRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioLoginResponse;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioFactory usuarioFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public AuthService(
            TokenService tokenService,
            UsuarioRepository usuarioRepository,
            UsuarioFactory usuarioFactory
    ) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioFactory = usuarioFactory;
    }

    public UsuarioLoginResponse login(UserLoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        Long idUsuario = ((Usuario) Objects.requireNonNull(auth.getPrincipal())).getId();
        var token = this.tokenService.generateToken((Usuario) auth.getPrincipal());

        return new UsuarioLoginResponse(idUsuario, token);
    }

    public MessageResponse register(UserRegisterRequest registerRequest) {

        // VERIFICA SE JÁ EXISTE PELO EMAIL
        if(this.usuarioRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        // CRIA E REGISTRA UM NOVO USUARIO
        this.usuarioFactory.createUsuario(registerRequest);
        return new MessageResponse(registerRequest.tipoConta()+" cadastrado com sucesso");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserDetails user = this.usuarioRepository.findByEmail(username)
        .orElseThrow(UserNotFoundException::new);
        
        return user;
    }
}
