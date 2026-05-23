package corallus.artConnect.artConnect.service;


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
import corallus.artConnect.artConnect.enums.ListaTipoConta;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContratanteRepository contratanteRepository;
    @Autowired
    private ArtistaRepository artistaRepository;

    public UsuarioLoginResponse login(UserLoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        Long idUsuario = ((Usuario) auth.getPrincipal()).getId();

        var token = this.tokenService.generateToken((Usuario) auth.getPrincipal());

        return new UsuarioLoginResponse(idUsuario, token);
    }

    public String register(UserRegisterRequest registerRequest, String tipoConta) {

        // VERIFICA SE JÁ EXISTE PELO EMAIL
        if(this.usuarioRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        String encriptedPassword = this.passwordEncoder.encode(registerRequest.senha());
        
        // Contratante CNPJ
        if(tipoConta.equalsIgnoreCase(ListaTipoConta.CONTRATANTE_CNPJ.name())) {

            Contratante contratante = new Contratante();
            contratante.setNome(registerRequest.nome());
            contratante.setEmail(registerRequest.email());
            contratante.setSenha(encriptedPassword);

            contratante.setCnpj(registerRequest.cnpj());
            contratante.setRazaoSocial(registerRequest.razaoSocial());

            contratante.setTipoConta(tipoConta.toUpperCase());

            this.contratanteRepository.save(contratante);

        // CONTRATANTE CPF
        } else if(tipoConta.equalsIgnoreCase(ListaTipoConta.CONTRATANTE_CPF.name())) {

            Contratante contratante = new Contratante();
            contratante.setNome(registerRequest.nome());
            contratante.setEmail(registerRequest.email());
            contratante.setSenha(encriptedPassword);

            contratante.setTipoConta(tipoConta.toUpperCase());

            this.contratanteRepository.save(contratante);

        // ARTISTA
        } else if(tipoConta.equalsIgnoreCase(ListaTipoConta.ARTISTA.name())) {

            Artista artista = new Artista();
            artista.setNome(registerRequest.nome());
            artista.setEmail(registerRequest.email());
            artista.setSenha(encriptedPassword);

            artista.setTipoConta(tipoConta.toUpperCase());

            this.artistaRepository.save(artista);

        // TIPO DE CONTA INVÁLIDO
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido");
        }


        return tipoConta+" cadastrado com sucesso";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserDetails user = this.usuarioRepository.findByEmail(username)
        .orElseThrow(()-> new UserNotFoundException());
        
        return user;
    }

    
}
