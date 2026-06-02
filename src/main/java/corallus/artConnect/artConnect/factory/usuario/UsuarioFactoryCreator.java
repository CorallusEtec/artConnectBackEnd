package corallus.artConnect.artConnect.factory.usuario;

import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.entity.atores.Usuario;

/**
 * Interface que compõe o padrão Factory para instância de um novo usuário
 * @author SamuMeneDev
 * @since 02/06/2026
 */
public interface UsuarioFactoryCreator {

    /**
     * Factory que instancia e perssiste um novo usuario baseado no tipo de conta desejada.
     *
     * @param registerRequest DTO com os dados do cadastro
     * @return Retorna o objeto do tipo usuario da generalização escolida
     */
    Usuario createUsuario(UserRegisterRequest registerRequest);
}
