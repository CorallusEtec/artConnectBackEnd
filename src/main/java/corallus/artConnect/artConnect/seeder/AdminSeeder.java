package corallus.artConnect.artConnect.seeder;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.factory.usuario.UsuarioFactory;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final UsuarioFactory usuarioFactory;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminSeeder(
            AdminRepository adminRepository,
            UsuarioFactory usuarioFactory
    ){
        this.adminRepository = adminRepository;
        this.usuarioFactory = usuarioFactory;
    }

    @Override
    public void run(String...args) {
        if (adminRepository.count() == 0) {
            //
            var admin = new UsuarioRegisterPrincipalRequest(
                    "André dos Santos",
                    "adminroot@outlook.com",
                    "Admin!2026",
                    ETipoConta.ADMIN.name(),
                    null
            );
            this.usuarioFactory.createUsuario(null, admin);
        }
    }
}
