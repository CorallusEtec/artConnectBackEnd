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
     * Metodo que adiciona dados específicos nas especialização de Usuario
     *
     * @param usuario Objeto que será complementado no método.
     * @param registerRequest DTO de cadastro caso haja atributos específicos para adicionar.
     * @return Retorna o usuário com os dados preenchidos.
     * @param <U> Tipo que representa as generalizações de Usuario.
     */

    <U extends Usuario> Usuario  composeUsuario (U usuario, UserRegisterRequest registerRequest);
}
