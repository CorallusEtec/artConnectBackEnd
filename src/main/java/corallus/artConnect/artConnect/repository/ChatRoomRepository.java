package corallus.artConnect.artConnect.repository;

import corallus.artConnect.artConnect.entity.ChatRoom;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT c FROM ChatRoom c WHERE " +
            ":usuario1 MEMBER OF c.usuarios AND " +
            ":usuario2 MEMBER OF c.usuarios")
    Optional<ChatRoom> findByUsers(Usuario usuario1, Usuario usuario2);
}
