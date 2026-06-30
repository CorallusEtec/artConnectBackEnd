package corallus.artConnect.artConnect.dto.response.denuncia;

import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoDenuncia;

import java.time.LocalDate;

public record DenunciaResponse (
        String titulo,
        LocalDate dataEnvio,
        Long idRecurso,
        ETipoDenuncia tipoDenuncia,
        AutorResponse autor,
        Status status
) {}
