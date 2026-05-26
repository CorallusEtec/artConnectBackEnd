package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.request.contato.ContatoEditRequest;
import corallus.artConnect.artConnect.dto.request.contato.ContatoSaveRequest;
import corallus.artConnect.artConnect.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;


    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody ContatoSaveRequest contato) {
        MessageResponse msg = this.contatoService.save(contato);
        
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{idContato}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long idContato) {
        MessageResponse msg = this.contatoService.delete(idContato);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<MessageResponse> edit(@PathVariable Long idContato, @RequestBody ContatoEditRequest editRequest) {
        MessageResponse msg = this.contatoService.edit(idContato, editRequest);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
}
