package corallus.artConnect.artConnect.repository;

import corallus.artConnect.artConnect.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT m FROM ChatMessage m WHERE m.id IN (" +
            "  SELECT MAX(m2.id) FROM ChatMessage m2 " +
            "  WHERE m2.sender.id = :userId OR m2.recipient.id = :userId " +
            "  GROUP BY " +
            "    CASE WHEN m2.sender.id = :userId THEN m2.recipient.id ELSE m2.sender.id END" +
            ") ORDER BY m.dataEnvio DESC")
    List<ChatMessage> findLastMessagesPerConversation(@Param("userId") Long userId);

    @Query(value = "SELECT m FROM ChatMessage m " +
            "WHERE (m.recipient.id = :userId AND m.sender.id = :recipientId) OR " +
            "(m.recipient.id = :recipientId AND m.sender.id = :userId) ORDER BY m.dataEnvio DESC",
            countQuery = "SELECT COUNT(m) FROM ChatMessage m " +
                    "WHERE (m.recipient.id = :userId AND m.sender.id = :recipientId) OR " +
                    "(m.recipient.id = :recipientId AND m.sender.id = :userId)")
    Page<ChatMessage> findAllHistory(@Param("userId") Long userId, @Param("recipientId") Long recipientId, Pageable pageable);
}
