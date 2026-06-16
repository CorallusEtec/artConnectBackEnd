package corallus.artConnect.artConnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity

/* Entidade de mensagem enviada no chat do sistema
 *
 * @since 16/06/2026
 */
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conteudo;

    private LocalDateTime dataEnvio = LocalDateTime.now();

    @ManyToOne
    @JsonIgnoreProperties({"contatos", "chatRooms"})
    private Usuario autor;

    @ManyToOne
    private ChatRoom chatRoom;

    @OneToOne
    private Status status;
}
