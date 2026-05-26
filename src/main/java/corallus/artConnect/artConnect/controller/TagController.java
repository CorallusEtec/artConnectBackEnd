package corallus.artConnect.artConnect.controller;

import java.util.List;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Tag;
import corallus.artConnect.artConnect.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody Tag tag) {
        MessageResponse msg = this.tagService.save(tag);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> lista = this.tagService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        MessageResponse msg = this.tagService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
}
