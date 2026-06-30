package corallus.artConnect.artConnect.entity;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoDenuncia;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Denuncia {
    private Usuario autor;
    private String titulo;
    private LocalDateTime dataEnvio = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private ETipoDenuncia tipoDenuncia;
    private Long idRecurso;

    private Status status;
}
