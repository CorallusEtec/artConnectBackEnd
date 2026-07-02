package corallus.artConnect.artConnect.dto.response.denuncia;

import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoDenuncia;

import java.time.LocalDateTime;

public record DenunciaResponse (
        Long id,
        String titulo,
        LocalDateTime dataEnvio,
        Long idRecurso,
        ETipoDenuncia tipoDenuncia,
        AutorResponse autor,
        Status status
) {}
