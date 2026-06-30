package corallus.artConnect.artConnect.entity;

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
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario sender;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario recipient;

    @Column(nullable = false)
    private String mensagem;

    private LocalDateTime dataEnvio = LocalDateTime.now();


    @OneToOne
    private Status status;
}
