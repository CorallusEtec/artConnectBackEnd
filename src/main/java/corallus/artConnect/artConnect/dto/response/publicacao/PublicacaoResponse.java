package corallus.artConnect.artConnect.dto.response.publicacao;

import java.time.LocalDateTime;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioPublicacaoResponse;
import lombok.Builder;

@Builder
public record PublicacaoResponse (

    Long id,
    String legenda,
    String urlMidia,
    LocalDateTime dataPublicacao,

    UsuarioPublicacaoResponse autor,
    Long totalComentarios,


    List<ReacaoResponse> reacoes
) {}

