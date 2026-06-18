package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioRegisterPrincipalRequest;
import corallus.artConnect.artConnect.entity.atores.Admin;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.S3Service;
import corallus.artConnect.artConnect.service.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <h2>UsuarioFactory</h2>
 * Classe que usa o factory method para instânciar usuarios
 * @author SamuMeneDev
 * @implNote Essa classe usa o padrão <i>Factory</i>
 */
@Component
public class UsuarioFactory implements UsuarioFactoryCreator {

    private final ArtistaFactory artistaFactory;
    private final ContratanteFactory contratanteFactory;
    private final AdminFactory adminFactory;
    private final PasswordEncoder passwordEncoder;
    private final StatusService statusService;
    private final S3Service s3Service;

    // INJEÇÃO DE DEPENDÊNCIA


    public UsuarioFactory(ArtistaFactory artistaFactory, ContratanteFactory contratanteFactory, AdminFactory adminFactory, PasswordEncoder passwordEncoder, StatusService statusService, S3Service s3Service) {
        this.artistaFactory = artistaFactory;
        this.contratanteFactory = contratanteFactory;
        this.adminFactory = adminFactory;
        this.passwordEncoder = passwordEncoder;
        this.statusService = statusService;
        this.s3Service = s3Service;
    }

    /**
     * Factory que instancia e perssiste um novo usuario baseado no tipo de conta desejada.
     *
     * @param principal DTO com os dados do cadastro
     * @return Retorna o objeto do tipo usuario da generalização escolida
     */


    public Usuario createUsuario(MultipartFile fotoPerfil, UsuarioRegisterPrincipalRequest principal) {
        switch (principal.tipoConta()) {
            case "ARTISTA" -> {
                var a = this.composeUsuario(new Artista(), principal, fotoPerfil);
                return this.artistaFactory.composeUsuario(a, principal, fotoPerfil);
            } case "CONTRATANTE" -> {
                var c = this.composeUsuario(new Contratante(), principal, fotoPerfil);
                return this.contratanteFactory.composeUsuario(c, principal, fotoPerfil);
            } case "ADMIN" -> {
                var admin = this.composeUsuario(new Admin(), principal, fotoPerfil);
                return this.adminFactory.composeUsuario(admin, principal, fotoPerfil);
            } default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }
    }


    public <U extends Usuario> Usuario composeUsuario(U usuario, UsuarioRegisterPrincipalRequest principal, MultipartFile fotoPerfil) {
        usuario.setEmail(principal.email());
        usuario.setId(null); // Garantir que criará um novo
        usuario.setNome(principal.nome());
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setSenha(this.passwordEncoder.encode(principal.senha()));

        // Adição da foto de perfil se fornecida
        if(!Objects.isNull(fotoPerfil)) {
            try {
                usuario.setFotoPerfilUrl(this.getFotoPerfilUrl(fotoPerfil));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // Adição de logradouro se fornecido
        if(!Objects.isNull(principal.details())) {
            usuario.setNomeLog(principal.details().nomeLog());
            usuario.setNumLog(principal.details().numLog());
            usuario.setCep(principal.details().cep());
            usuario.setBairro(principal.details().bairro());
            usuario.setComplemento(principal.details().complemento());
            usuario.setCidade(principal.details().cidade());
            usuario.setUf(principal.details().uf());
        }

        usuario.setStatus(this.statusService.generateStatus());
        return usuario;
    }

    private String getFotoPerfilUrl(MultipartFile fotoPerfil) throws Exception {
        String contentType = fotoPerfil.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Apenas imagens são permitidas");
        }

        return this.s3Service.uploadFotoPerfil(fotoPerfil);
    }
}
