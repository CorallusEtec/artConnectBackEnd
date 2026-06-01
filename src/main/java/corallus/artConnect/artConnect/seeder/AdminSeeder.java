package corallus.artConnect.artConnect.seeder;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import corallus.artConnect.artConnect.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;

    private final AuthService authService;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminSeeder(AdminRepository adminRepository, AuthService authService) {
        this.adminRepository = adminRepository;
        this.authService = authService;
    }

    @Override
    public void run(String...args) throws Exception {
        if (adminRepository.count() == 0) {
            //
            var admin = new UserRegisterRequest("André dos Santos",
                    "adminroot@outlook.com",
                    "Admin!2026",
                    ETipoConta.ADMIN, null, null
            );
            authService.registerAdmin(admin);
        }
    }
}
