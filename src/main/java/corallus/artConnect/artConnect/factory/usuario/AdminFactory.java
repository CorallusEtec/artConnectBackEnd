package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.repository.atores.AdminRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AdminFactory implements UsuarioFactoryCreator{

    private final AdminRepository adminRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public AdminFactory(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public <U extends Usuario> Usuario composeUsuario(U usuario, UsuarioRegisterPrincipalRequest principal, MultipartFile fotoPerfil) {
        usuario.setTipoConta(ETipoConta.ADMIN);
        if(this.adminRepository.count() > 0) {
            throw new IllegalArgumentException("Esse tipo de conta não pode ser criado.");
        } else {
            return this.adminRepository.save((Admin) usuario);
        }

    }
}
