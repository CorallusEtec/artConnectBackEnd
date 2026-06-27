package corallus.artConnect.artConnect.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.factory.usuario.UsuarioFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioLoginRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioLoginResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioFactory usuarioFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public AuthService(@Lazy AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository, UsuarioFactory usuarioFactory) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioFactory = usuarioFactory;
    }

    public UsuarioLoginResponse login(UsuarioLoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Status status = ((Usuario) Objects.requireNonNull(auth.getPrincipal())).getStatus();
        Long idUsuario = ((Usuario) Objects.requireNonNull(auth.getPrincipal())).getId();
        ETipoConta tipoConta = ((Usuario) auth.getPrincipal()).getTipoConta();
        String token = this.tokenService.generateToken((Usuario) auth.getPrincipal());

        return new UsuarioLoginResponse(idUsuario, token, tipoConta, status);
    }

    public MessageApiResponse register(MultipartFile fotoPerfil, String principalJson) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        UsuarioRegisterPrincipalRequest principal = objectMapper.readValue(
                principalJson,
                UsuarioRegisterPrincipalRequest.class
        );

        // VERIFICA SE JÁ EXISTE PELO EMAIL
        if(this.usuarioRepository.existsByEmail(principal.email())) {
            throw new UserAlreadyExistsException();
        }

        // CRIA E REGISTRA UM NOVO USUARIO
        this.usuarioFactory.createUsuario(fotoPerfil, principal);
        return new MessageApiResponse(principal.tipoConta()+" cadastrado com sucesso");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(username)
        .orElseThrow(UserNotFoundException::new);

    }
}
