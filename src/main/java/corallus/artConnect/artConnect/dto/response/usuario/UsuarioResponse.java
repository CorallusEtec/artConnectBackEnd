package corallus.artConnect.artConnect.dto.response.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoDetailsResponse;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.mapper.contato.ContatoMapper;

public record UsuarioResponse(
    Long id,
    String nome,
    String email,
    ETipoConta tipoConta,
    Status status,
    LocalDateTime dataCriacao,

    String fotoPerfilUrl,
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,

    List<PublicacaoDetailsResponse> publicacoes,

    String textoBio,
    List<ContatoResponse> contatos
) {}
