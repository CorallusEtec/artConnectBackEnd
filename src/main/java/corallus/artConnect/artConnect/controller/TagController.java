package corallus.artConnect.artConnect.controller;

import java.util.List;

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
    public ResponseEntity<String> save(@RequestBody Tag tag) {
        String msg = this.tagService.save(tag);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> lista = this.tagService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String msg = this.tagService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
}
