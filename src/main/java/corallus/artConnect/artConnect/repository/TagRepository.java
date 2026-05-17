package corallus.artConnect.artConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import corallus.artConnect.artConnect.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
