package corallus.artConnect.artConnect.seeders;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.reacao.TipoReacao;
import corallus.artConnect.artConnect.enums.ListaTipoReacao;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import corallus.artConnect.artConnect.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthService authService;

    @Override
    public void run(String...args) throws Exception {
        if (adminRepository.count() == 0) {
            //
            var admin = new UserRegisterRequest("André dos Santos",
                    "adminroot@outlook.com",
                    "Admin!2026",
                    "ADMIN", null, null
            );
            authService.registerAdmin(admin);
        }
    }
}
