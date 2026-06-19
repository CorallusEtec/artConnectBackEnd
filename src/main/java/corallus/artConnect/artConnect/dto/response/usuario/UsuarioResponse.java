package corallus.artConnect.artConnect.dto.response.usuario;

import java.time.LocalDateTime;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;

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

    String textoBio,
    List<ContatoResponse> contatos
) {}
