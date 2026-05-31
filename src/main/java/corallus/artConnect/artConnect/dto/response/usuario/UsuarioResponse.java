package corallus.artConnect.artConnect.dto.response.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.status.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;

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
            usuario.getContatos().stream()
            .map(
                c-> new ContatoResponse.builder()
                .setIdContato(c.getId())
                .setTipoContatoResponse(
                    
                    new TipoContatoResponse.builder()
                    .setIdTipoContato(c.getTipoContato().getId())
                    .setTipoContato(c.getTipoContato().getTipoContato())
                    .build()
                )
                .setValorContato(c.getValorContato())
                .build()
            )
            .collect(Collectors.toList())
        );
    }
}
