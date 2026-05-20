package corallus.artConnect.artConnect.dto.response.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.contato.Contato;

public record UsuarioResponse(
    Long id,
    String nome,
    String email,
    String tipoConta,
    Status status,
    LocalDateTime dataCriacao,

    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,

    String textoBio,
    Set<Seguida> seguidores,
    Set<Seguida> seguido,
    List<Contato> contatos
) {
    public static UsuarioResponse toDTO (Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTipoConta(),
            usuario.getStatus(),
            usuario.getDataCriacao(),

            usuario.getNomeLog(),
            usuario.getNumLog(),
            usuario.getCep(),
            usuario.getBairro(),
            usuario.getComplemento(),
            usuario.getCidade(),
            usuario.getUf(),

            usuario.getTextoBio(),
            usuario.getSeguidores(),
            usuario.getSeguido(),
            usuario.getContatos()
        );
    }
}
