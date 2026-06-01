package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Admin;
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

@Service
public class AuthService implements UserDetailsService {
    @Lazy
    private final AuthenticationManager authenticationManager;

    private final StatusService statusService;

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final ContratanteRepository contratanteRepository;

    private final ArtistaRepository artistaRepository;

    private final AdminRepository adminRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       StatusService statusService,
                       TokenService tokenService,
                       UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       ContratanteRepository contratanteRepository,
                       ArtistaRepository artistaRepository,
                       AdminRepository adminRepository) {
        this.authenticationManager = authenticationManager;
        this.statusService = statusService;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.contratanteRepository = contratanteRepository;
        this.artistaRepository = artistaRepository;
        this.adminRepository = adminRepository;
    }

    public UsuarioLoginResponse login(UserLoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        Long idUsuario = ((Usuario) auth.getPrincipal()).getId();

        var token = this.tokenService.generateToken((Usuario) auth.getPrincipal());

        return new UsuarioLoginResponse(idUsuario, token);
    }

    public MessageResponse register(UserRegisterRequest registerRequest) {

        // VERIFICA SE JÁ EXISTE PELO EMAIL
        if(this.usuarioRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        String encriptedPassword = this.passwordEncoder.encode(registerRequest.senha());

        // Contratante CNPJ
        if(registerRequest.tipoConta() == ETipoConta.CONTRATANTE_CNPJ) {
            Contratante contratante = new Contratante();
            commonAtributtes(contratante, registerRequest);
            contratante.setSenha(encriptedPassword);
            contratante.setCnpj(registerRequest.cnpj());
            contratante.setRazaoSocial(registerRequest.razaoSocial());

            this.contratanteRepository.save(contratante);

        // CONTRATANTE CPF
        } else if(registerRequest.tipoConta() == ETipoConta.CONTRATANTE_CPF) {

            Contratante contratante = new Contratante();
            commonAtributtes(contratante, registerRequest);
            this.contratanteRepository.save(contratante);

        // ARTISTA
        } else if(registerRequest.tipoConta() == ETipoConta.ARTISTA) {

            Artista artista = new Artista();
            commonAtributtes(artista, registerRequest);
            artista.setSenha(encriptedPassword);

            this.artistaRepository.save(artista);
        // TIPO DE CONTA INVÁLIDO
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido");
        }
        return new MessageResponse(registerRequest.tipoConta()+" cadastrado com sucesso");
    }

    public void registerAdmin(UserRegisterRequest request) {

        Admin admin = new Admin();
        commonAtributtes(admin, request);
        admin.setSenha(this.passwordEncoder.encode(request.senha()));
        this.adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserDetails user = this.usuarioRepository.findByEmail(username)
        .orElseThrow(UserNotFoundException::new);
        
        return user;
    }

    public void commonAtributtes(Usuario u, UserRegisterRequest registerRequest) {
        u.setNome(registerRequest.nome());
        u.setEmail(registerRequest.email());
        u.setTipoConta(registerRequest.tipoConta());
        u.setDataCriacao(LocalDateTime.now());
        u.setStatus(this.statusService.generateStatus());
    }
    
}
