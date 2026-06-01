package corallus.artConnect.artConnect.dto.response.publicacao;

import java.time.LocalDateTime;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoDetailsResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;

public record PublicacaoResponse (

    Long id,
    String legenda,
    String urlMidia,
    LocalDateTime dataPublicacao,

    UsuarioResponse autor,
    Integer totalReacoes,
    Integer totalComentarios,

    List<ReacaoDetailsResponse> reacoes
) {}

