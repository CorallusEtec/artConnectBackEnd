package corallus.artConnect.artConnect.dto.response.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.status.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.mapper.contato.ContatoMapper;

public record UsuarioResponse(
    Long id,
    String nome,
    String email,
    ETipoConta tipoConta,
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
    List<ContatoResponse> contatos
) {

    public static UsuarioResponse toDTO(Usuario u, ContatoMapper contatoMapper) {
        return new UsuarioResponse(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getTipoConta(),
                u.getStatus(),
                u.getDataCriacao(),

                u.getNomeLog(),
                u.getNumLog(),
                u.getCep(),
                u.getBairro(),
                u.getComplemento(),
                u.getCidade(),
                u.getUf(),

                u.getTextoBio(),
                u.getSeguidores(),
                u.getSeguido(),
                contatoMapper.toDTOList(u.getContatos())
        );
    }
}
