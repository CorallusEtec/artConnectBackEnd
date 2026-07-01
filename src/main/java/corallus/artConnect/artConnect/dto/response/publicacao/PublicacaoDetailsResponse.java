package corallus.artConnect.artConnect.dto.response.publicacao;

import java.time.LocalDateTime;

import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoMidia;

public record PublicacaoDetailsResponse (

    Long id,
    String legenda,
    String urlMidia,
    ETipoMidia tipoMidia,
    LocalDateTime dataPublicacao,

    Status statusPublicacao,
    AutorResponse autor
) {}

