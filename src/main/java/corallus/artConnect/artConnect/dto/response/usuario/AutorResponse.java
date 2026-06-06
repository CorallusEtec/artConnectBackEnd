package corallus.artConnect.artConnect.dto.response.usuario;

import corallus.artConnect.artConnect.enumeration.ETipoConta;

/**
 * Response que trás dados do autor seja de uma publicação ou de um comentário.
 *
 * @param id ID do autor do recurso.
 * @param nome Nome do autor do recurso.
 * @param tipoConta Tipo de conta do autor do recurso.
 */
public record AutorResponse(
        Long id,
        String nome,
        ETipoConta tipoConta
) {
}
