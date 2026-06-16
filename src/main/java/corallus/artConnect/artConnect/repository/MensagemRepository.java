package corallus.artConnect.artConnect.repository;

import corallus.artConnect.artConnect.entity.Mensagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    Page<Mensagem> findAllByChatRoom_Id(Long chatRoomId, Pageable pageable);
}
