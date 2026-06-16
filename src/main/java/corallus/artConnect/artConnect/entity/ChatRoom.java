package corallus.artConnect.artConnect.entity;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom")
    private List<Mensagem> mensagens;

    @Size(min = 2, max = 2, message = "O chat precisa ter exatamente 2 usuários")
    @ManyToMany
    @JoinTable(
            name = "chat_room_usuarios",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    // Construtor para declarar os dois usuarios na sala
    public ChatRoom(Usuario usuario1, Usuario usuario2) {
        if(usuario1.equals(usuario2)) {
            throw new IllegalArgumentException("Os usuários devem ser diferentes");
        }
        this.usuarios.add(usuario1);
        this.usuarios.add(usuario2);
    }

}
