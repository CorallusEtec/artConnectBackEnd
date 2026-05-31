package corallus.artConnect.artConnect.service;

import java.util.List;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Tag;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.TagRepository;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public MessageResponse save(Tag tag) {
        if(tag.equals(null)) {
            throw new IllegalArgumentException("A tag não pode ser nula");
        }

        tag.setId(null);
        
        this.tagRepository.save(tag);
        return new MessageResponse("Tag criada com sucesso");
    } 

    public List<Tag> findAll() {
        return this.tagRepository.findAll();
    }

    public MessageResponse delete(Long id) {
        if(!this.tagRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tag não encontrada.");
        }

        this.tagRepository.deleteById(id);

        return new MessageResponse("Tag deletada com sucesso");
    }
}
