package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminFactory implements UsuarioFactoryCreator{

    private final AdminRepository adminRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminFactory(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UserRegisterRequest registerRequest) {
        usuario.setTipoConta(ETipoConta.ADMIN);
        return this.adminRepository.save((Admin) usuario);
    }
}
