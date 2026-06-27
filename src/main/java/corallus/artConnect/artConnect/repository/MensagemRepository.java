package corallus.artConnect.artConnect.repository;

import corallus.artConnect.artConnect.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<ChatMessage, Long> {

}
