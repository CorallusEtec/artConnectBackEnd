package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
