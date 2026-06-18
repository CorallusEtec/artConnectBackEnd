package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;

import java.util.List;

/**
 * Dados de cadastro opcionais
 */
public record UsuarioRegisterDetailsRequest(
        String nomeLog,
        Short numLog,
        String cep,
        String bairro,
        String complemento,
        String cidade,
        String uf,

        String nomeArtistico,
    Arte arte,
    List<GeneroArte> generosArte
) {
}
