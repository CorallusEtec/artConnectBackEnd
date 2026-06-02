package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import corallus.artConnect.artConnect.service.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminFactory implements UsuarioFactoryCreator{

    private final AdminRepository adminRepository;
    private final StatusService statusService;
    private final PasswordEncoder passwordEncoder;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminFactory(
            AdminRepository adminRepository,
            StatusService statusService,
            PasswordEncoder passwordEncoder
    ) {
        this.adminRepository = adminRepository;
        this.statusService = statusService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario createUsuario(UserRegisterRequest registerRequest) {
        Admin admin = new Admin();
        String senha = this.passwordEncoder.encode(registerRequest.senha());
        admin.setSenha(senha);

        admin.setTipoConta(ETipoConta.ADMIN);
        admin.setNome(registerRequest.nome());
        admin.setEmail(registerRequest.email());
        admin.setDataCriacao(LocalDateTime.now());
        admin.setStatus(this.statusService.generateStatus());

        return this.adminRepository.save(admin);
    }
}
